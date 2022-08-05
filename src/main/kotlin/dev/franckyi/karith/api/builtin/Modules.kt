package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.module

/**
 * Object containing some useful modules.
 *
 * @see dev.franckyi.karith.api.KthModule
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
        module {
            withOperators(Operators.TIMES, Operators.DIVIDE, Operators.REMAINDER, Operators.PLUS, Operators.MINUS)
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
        module {
            withOperators(
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
        module {
            withFunctions(
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
        module {
            withFunctions(
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
        module {
            withFunctions(
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
        module { includeAll(MATH_UTIL, MATH_LOG, MATH_TRIG) }
    }
}