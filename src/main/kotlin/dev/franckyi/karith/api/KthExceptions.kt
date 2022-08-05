package dev.franckyi.karith.api

/**
 * Base class for all exceptions thrown by Karith.
 */
sealed class KthException(message: String) : Exception(message)

/**
 * Thrown during tokenization if the input string contains an illegal token.
 *
 * @property token The illegal token.
 * @property position The position of the illegal token.
 */
class KthIllegalTokenException(val token: String, val position: Int) :
    KthException("Illegal token \"$token\" at position $position")

/**
 * Thrown during token sorting if the input string contains mismatched parentheses.
 *
 * @property index The index of the parentheses token.
 */
class KthMismatchedParenthesesException(val index: Int) : KthException("Mismatched parentheses at token index $index")

/**
 * Thrown during computing if an input variable is not defined.
 *
 * @property variable The name of variable that is not defined.
 */
class KthUndefinedVariableException(val variable: String) : KthException("Variable \"$variable\" is not defined")

/**
 * Thrown during computing if a function / operator doesn't have enough arguments / operands.
 *
 * @property name The name of the function or operator.
 */
class KthInsufficientOperandsException(type: String, val name: String) :
    KthException("Stack contains too few operands to apply $type \"$name\"")

/**
 * Thrown during computing if it encounters an unknown token.
 *
 * @property token The unknown token.
 */
class KthUnknownTokenException(val token: KthToken) : KthException("Unknown token \"$token\"")
