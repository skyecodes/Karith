package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal fun interface KthTokenizer {
    operator fun invoke(expr: String, declaredVars: List<String>?): Pair<List<KthToken>, Set<String>>
}

internal fun KthTokenizer(elementMap: Map<String, KthElement>, combinerOperator: KthOperator) =
    KthTokenizer { expr, declaredVars ->
        with(KthTokenizerState(elementMap, combinerOperator, declaredVars)) {
            expr.forEach {
                if (it.isWhitespace()) {
                    processBuffer()
                } else if (it in KthSymbol.inverse) {
                    processBuffer()
                    addToken(KthSymbol.inverse.getValue(it))
                } else {
                    if ((it.isLetterOrDigit() || it in "_.-+") != isBufferAlphanum) {
                        processBuffer()
                        isBufferAlphanum = !isBufferAlphanum
                    }
                    buffer += it
                }
                index++
            }
            processBuffer()
            tokens to detectedVariables
        }
    }

internal data class KthTokenizerState(
    val elementMap: Map<String, KthElement>,
    val combinerOperator: KthOperator,
    val declaredVars: List<String>?,
    var index: Int = 0,
    var buffer: String = "",
    var isBufferAlphanum: Boolean = false,
    val tokens: MutableList<KthToken> = mutableListOf<KthToken>(),
    val detectedVariables: MutableSet<String> = mutableSetOf<String>()
) {
    fun addToken(token: KthToken) {
        if (tokens.isNotEmpty() && token !is KthOperator) {
            val last = tokens.last()
            if (last !is KthOperator && ((last == KthSymbol.RIGHT_PARENTHESIS || last !is KthSymbol) && token !is KthSymbol || token == KthSymbol.LEFT_PARENTHESIS && (last !is KthSymbol && last !is KthFunction))) {
                tokens += combinerOperator
            }
        }
        tokens += token
    }

    fun processBuffer() {
        while (buffer.isNotEmpty()) {
            val last = tokens.lastOrNull()
            var subBufferDone = false
            for (i in (0..buffer.length).reversed()) {
                val subBuffer = buffer.substring(0, i)
                val num = subBuffer.toDoubleOrNull()
                when {
                    num != null && !(subBuffer[0] in "+-" && (last is KthNumber || last is KthConstant || last is KthVariable || last == KthSymbol.RIGHT_PARENTHESIS)) -> {
                        addToken(KthNumber(num))
                    }

                    subBuffer in elementMap -> {
                        addToken(elementMap.getValue(subBuffer))
                    }

                    declaredVars != null && subBuffer in declaredVars -> {
                        detectedVariables += subBuffer
                        addToken(KthVariable(subBuffer))
                    }

                    else -> {
                        continue
                    }
                }
                buffer = buffer.substring(i, buffer.length)
                subBufferDone = true
                break
            }
            if (subBufferDone) {
                continue
            } else if (declaredVars == null && isBufferAlphanum) {
                addToken(KthVariable(buffer))
                detectedVariables += buffer
                buffer = ""
            } else {
                throw KthIllegalTokenException(buffer, index - buffer.length)
            }
        }
    }
}