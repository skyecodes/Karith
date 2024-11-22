package com.skyecodes.karith.impl

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.api.KthSymbol
import com.skyecodes.karith.api.KthToken
import com.skyecodes.karith.api.KthUndefinedVariableException
import com.skyecodes.karith.api.KthUnknownTokenException
import com.skyecodes.karith.api.builtin.Constants
import com.skyecodes.karith.api.builtin.Functions
import com.skyecodes.karith.api.builtin.Operators
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Burst
internal class KthComputerTest {
    @Test
    fun testInvoke_ShouldReturnCorrectResult(
        args: Arguments = burstValues(
            Arguments(listOf(num(2), Constants.PI, Operators.PLUS), emptyMap<String, Number>(), 2 + PI),
            Arguments(listOf(num(2), variable("a"), Operators.TIMES), mapOf("a" to 3), 6.0),
            Arguments(listOf(num(2), variable("a"), Functions.POW), mapOf("a" to 3), 8.0),
        )
    ) {
        val (tokens, variables, expectedResult) = args
        assertEquals(KthComputer(tokens, variables), expectedResult)
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenVariableNotSet() {
        assertFailsWith<KthUndefinedVariableException> { KthComputer(listOf(variable("a")), emptyMap()) }
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenUnknownToken() {
        assertFailsWith<KthUnknownTokenException> {
            KthComputer(
                listOf(
                    KthSymbol.LEFT_PARENTHESIS,
                    num(3),
                    KthSymbol.RIGHT_PARENTHESIS
                ), emptyMap()
            )
        }
    }

    data class Arguments(val tokens: List<KthToken>, val variables: Map<String, Number>, val expectedResult: Double)
}