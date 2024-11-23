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

package com.skyecodes.karith.builtin

import com.skyecodes.karith.buildModule

/**
 * Object containing some useful modules.
 *
 * @see com.skyecodes.karith.KthModule
 */
object Modules {

    /**
     * Module that includes all basic operators and registers [Operators.TIMES] as combiner operator.
     *
     * Includes the following operators:
     * * [Operators.PLUS]
     * * [Operators.MINUS]
     * * [Operators.TIMES]
     * * [Operators.DIVIDE]
     * * [Operators.REMAINDER]
     */
    val BASE by lazy {
        buildModule {
            withOperator(Operators.TIMES, Operators.DIVIDE, Operators.REMAINDER, Operators.PLUS, Operators.MINUS)
            withCombinerOperator(Operators.TIMES)
        }
    }

    /**
     * Module that includes bitwise operators and functions.
     *
     * Includes the following operators:
     * * [Operators.AND]
     * * [Operators.OR]
     * * [Operators.XOR]
     * * [Operators.SHIFT_LEFT]
     * * [Operators.SHIFT_RIGHT]
     * * [Operators.UNSIGNED_SHIFT_RIGHT]
     *
     * Includes the following function:
     * * [Functions.NOT]
     */
    val BITWISE by lazy {
        buildModule {
            withOperator(
                Operators.SHIFT_LEFT, Operators.SHIFT_RIGHT, Operators.UNSIGNED_SHIFT_RIGHT, Operators.AND,
                Operators.OR, Operators.XOR
            )
            withFunction(Functions.NOT)
        }
    }

    /**
     * Module that includes utility functions.
     *
     * Includes the following function:
     * * [Functions.ABS]
     * * [Functions.CEIL]
     * * [Functions.FLOOR]
     * * [Functions.HYPOT]
     * * [Functions.MAX]
     * * [Functions.MIN]
     * * [Functions.NEXT_DOWN]
     * * [Functions.NEXT_TOWARDS]
     * * [Functions.NEXT_UP]
     * * [Functions.POW]
     * * [Functions.ROUND]
     * * [Functions.SIGN]
     * * [Functions.SQRT]
     * * [Functions.TRUNCATE]
     * * [Functions.ULP]
     * * [Functions.WITH_SIGN]
     */
    val MATH_UTIL by lazy {
        buildModule {
            withFunction(
                Functions.ABS, Functions.CEIL, Functions.FLOOR, Functions.HYPOT,
                Functions.MAX, Functions.MIN, Functions.NEXT_DOWN, Functions.NEXT_TOWARDS, Functions.NEXT_UP,
                Functions.POW, Functions.ROUND, Functions.SIGN, Functions.SQRT, Functions.TRUNCATE, Functions.ULP,
                Functions.WITH_SIGN
            )
        }
    }

    /**
     * Module that includes logarithmic and exponential functions and constants.
     *
     * Includes the following functions:
     * * [Functions.EXP]
     * * [Functions.EXPM1]
     * * [Functions.LN]
     * * [Functions.LN1P]
     * * [Functions.LOG]
     * * [Functions.LOG10]
     * * [Functions.LOG2]
     *
     * Includes the following constant:
     * * [Constants.E]
     */
    val MATH_LOG by lazy {
        buildModule {
            withFunction(
                Functions.EXP, Functions.EXPM1, Functions.LN, Functions.LN1P, Functions.LOG,
                Functions.LOG10, Functions.LOG2
            )
            withConstant(Constants.E)
        }
    }

    /**
     * Module that includes trigonometric functions and constants.
     *
     * Includes the following functions:
     * * [Functions.ACOS]
     * * [Functions.ACOSH]
     * * [Functions.ASIN]
     * * [Functions.ASINH]
     * * [Functions.ATAN]
     * * [Functions.ATAN2]
     * * [Functions.ATANH]
     * * [Functions.COS]
     * * [Functions.COSH]
     * * [Functions.SIN]
     * * [Functions.SINH]
     * * [Functions.TAN]
     * * [Functions.TANH]
     *
     * Includes the following constant:
     * * [Constants.PI]
     */
    val MATH_TRIG by lazy {
        buildModule {
            withFunction(
                Functions.ACOS, Functions.ACOSH, Functions.ASIN, Functions.ASINH, Functions.ATAN,
                Functions.ATAN2, Functions.ATANH, Functions.COS, Functions.COSH, Functions.SIN, Functions.SINH,
                Functions.TAN, Functions.TANH
            )
            withConstant(Constants.PI)
        }
    }

    /**
     * Module that includes utility, logarithmic, exponential and trigonometric functions and constants.
     *
     * Includes the following modules:
     * * [Modules.MATH_UTIL]
     * * [Modules.MATH_LOG]
     * * [Modules.MATH_TRIG]
     */
    val MATH by lazy {
        buildModule { include(MATH_UTIL, MATH_LOG, MATH_TRIG) }
    }
}