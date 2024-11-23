/*
 * Copyright (c) 2024 skyecodes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.skyecodes.karith.impl

import com.skyecodes.karith.*

internal fun interface KthTokenizer {
    operator fun invoke(expr: String, declaredVars: List<String>?): KthTokenizerResult
}

internal fun KthTokenizer(elementMap: Map<String, KthElement>, combinerOperator: KthOperator?) =
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
            KthTokenizerResult(tokens, detectedVariables)
        }
    }

internal data class KthTokenizerResult(
    val tokens: List<KthToken>,
    val detectedVariables: Set<String>,
)

internal data class KthTokenizerState(
    val elementMap: Map<String, KthElement>,
    val combinerOperator: KthOperator?,
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
                tokens += (combinerOperator ?: throw KthUndefinedCombinerOperatorException(index))
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

                    subBuffer in elementMap -> addToken(elementMap.getValue(subBuffer))

                    declaredVars != null && subBuffer in declaredVars -> {
                        detectedVariables += subBuffer
                        addToken(KthVariable(subBuffer))
                    }

                    else -> continue
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