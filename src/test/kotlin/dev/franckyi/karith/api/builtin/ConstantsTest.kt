package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.KthConstant
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class ConstantsTest {
    companion object {
        @JvmStatic
        fun provideTestConstant_ShouldReturnCorrectValue(): Stream<Arguments> = Stream.of(
            Arguments.of(Constants.PI, Math.PI),
            Arguments.of(Constants.E, Math.E)
        )
    }

    @ParameterizedTest
    @MethodSource("provideTestConstant_ShouldReturnCorrectValue")
    fun testConstant_ShouldReturnCorrectValue(constant: KthConstant, value: Double) {
        assertEquals(value, constant.value)
    }
}