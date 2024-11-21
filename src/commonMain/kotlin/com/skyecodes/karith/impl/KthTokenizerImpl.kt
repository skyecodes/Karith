package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal class KthTokenizerImpl(
    private val elementMap: MutableMap<String, KthElement>,
    private val combinerOperator: KthOperator
) : KthTokenizer {
    private var index = 0
    private var buffer = ""
    private var isBufferAlphanum = false
    private var tokens = mutableListOf<KthToken>()
    private var detectedVariables = mutableSetOf<String>()
    private var declaredVars: List<String>? = null

    override fun tokenize(expr: String, declaredVars: List<String>?): Pair<List<KthToken>, Set<String>> {
        cleanup(declaredVars)
        expr.forEach {
            if (it.isWhitespace()) {
                clearBuffer()
            } else if (it in KthSymbol.inverse) {
                clearBuffer()
                addToken(KthSymbol.inverse[it]!!)
            } else {
                if ((it.isLetterOrDigit() || it in "_.-+") != isBufferAlphanum) {
                    clearBuffer()
                    isBufferAlphanum = !isBufferAlphanum
                }
                buffer += it
            }
            index++
        }
        clearBuffer()
        return tokens to detectedVariables
    }

    private fun cleanup(declaredVars: List<String>?) {
        index = 0
        buffer = ""
        isBufferAlphanum = false
        tokens = mutableListOf()
        detectedVariables = mutableSetOf()
        this.declaredVars = declaredVars
    }

    private fun addToken(token: KthToken) {
        if (tokens.isNotEmpty() && token !is KthOperator) {
            val last = tokens.last()
            if (last !is KthOperator && ((last == KthSymbol.RIGHT_PARENTHESIS || last !is KthSymbol) && token !is KthSymbol || token == KthSymbol.LEFT_PARENTHESIS && (last !is KthSymbol && last !is KthFunction))) {
                tokens += combinerOperator
            }
        }
        tokens += token
    }

    private fun clearBuffer() {
        while (buffer.isNotEmpty()) {
            val last = tokens.lastOrNull()
            var subBufferDone = false
            for (i in (0..buffer.length).reversed()) {
                val subBuffer = buffer.substring(0, i)
                val num = subBuffer.toDoubleOrNull()
                if (num != null && !(subBuffer[0] in "+-" && (last is KthNumber || last is KthConstant || last is KthVariable || last == KthSymbol.RIGHT_PARENTHESIS))) {
                    addToken(KthNumber(num))
                } else if (subBuffer in elementMap) {
                    addToken(elementMap[subBuffer]!!)
                } else if (declaredVars != null && subBuffer in declaredVars!!) {
                    detectedVariables += subBuffer
                    addToken(KthVariable(subBuffer))
                } else {
                    continue
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