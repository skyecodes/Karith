package com.skyecodes.karith.api.builtin

import kotlin.math.E
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantsTest {
    @Test
    fun testConstant_ShouldReturnCorrectValue() {
        assertEquals(Constants.PI.value, PI)
        assertEquals(Constants.E.value, E)
    }
}