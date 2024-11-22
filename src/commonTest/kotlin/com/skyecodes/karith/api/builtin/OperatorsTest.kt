package com.skyecodes.karith.api.builtin

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.api.KthOperator
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

@Burst
class OperatorsTest {
    @Test
    fun testOperator_ShouldReturnCorrectValue(
        args: Arguments = burstValues(
            Arguments(Operators.POWER, 3, 2, 3.0.pow(2)),
            Arguments(Operators.TIMES, 3, 2, 3 * 2),
            Arguments(Operators.DIVIDE, 3, 2, 3.0 / 2.0),
            Arguments(Operators.REMAINDER, 3, 2, 3 % 2),
            Arguments(Operators.PLUS, 3, 2, 3 + 2),
            Arguments(Operators.MINUS, 3, 2, 3 - 2),
            Arguments(Operators.SHIFT_LEFT, 3, 2, 3 shl 2),
            Arguments(Operators.SHIFT_RIGHT, 3, 2, 3 shr 2),
            Arguments(Operators.UNSIGNED_SHIFT_RIGHT, 3, 2, 3 ushr 2),
            Arguments(Operators.AND, 3, 2, 3 and 2),
            Arguments(Operators.XOR, 3, 2, 3 xor 2),
            Arguments(Operators.OR, 3, 2, 3 or 2)
        )
    ) {
        val (operator, a, b, expectedValue) = args
        assertEquals(expectedValue.toDouble(), operator(a.toDouble(), b.toDouble()))
    }

    data class Arguments(val operator: KthOperator, val a: Number, val b: Number, val expectedValue: Number)
}