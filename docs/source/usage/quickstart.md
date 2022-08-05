# Quickstart

The Karith library is designed to be easy to use and highly configurable.

## Simple example

```kotlin
val res = "1 + 2".result() // 3.0
val res = "3a + b".resultWith("a" to 1, "b" to 2) // 5.0
val res = "6 % 4".intResult() // 2
val res = "6 / min(x,y)".intResultWith("x" to 2, "y" to 3) // 3
```

> Note: The above example uses a global context. It is recommended to build and use your own context instead.

## Creating a context

First of all, you need to create a context. A context is an object that contains all the operators, variables and
functions that you can parse in an expression. A context can also cache expressions and their result.

A context is usually created by including some modules, operators, variables and functions in it. Modules are
collections of operators, variables and functions. You can find more information about builtin modules, operators,
variables and functions in the [Builtin elements](advanced.md#builtin-elements) section.

You can easily create your own context using helper functions. Here is an example:

```kotlin
// creates a context with + and - operators only
val ctx = context { withOperators(Operators.PLUS, Operators.MINUS) }

// creates a context with basic operators (+, -, *, / and %)
val ctx = baseContext()

// creates a context with basic operators and functions
val ctx = defaultContext()
```

## Parsing an expression

You can parse an expression using the `expression` or `expressionWith` method of a context.

```kotlin
val expr = ctx.expression("1 + 2")
val expr = ctx.expressionWith("3a + b", "a", "b")
val expr = ctx.expression("6 % 4")
val expr = ctx.expressionWith("6 / min(x,y)", "x", "y")
```

## Computing a result

You can compute the result of an expression using the `result`, `intResult` `resultWith`, or `intResultWith` method of
an expression.

```kotlin
val expr = ctx.expression("1 + 2")
val res = expr.result() // 3.0

val expr = ctx.expressionWith("3a + b", "a", "b")
val res = expr.resultWith("a" to 1, "b" to 2) // 5.0

val expr = ctx.expression("6 % 4")
val res = expr.intResult() // 2

val expr = ctx.expressionWith("6 / min(x,y)", "x", "y")
val res = expr.intResultWith("x" to 2, "y" to 3) // 3
```