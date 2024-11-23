## Custom elements

Karith provides an easy way to extend the builtin sets of operators, functions, constants and modules to create your
own.

```kotlin
val atOperator = createOperator("@", 12) { a, b -> (a + b) * (a - b) }
// Uses the "@" key
// Same precedence as multiplication and division
// Left-associative
// 2 @ 3 = (2 + 3) * (2 - 3)

val dollarOperator = createOperator("$", 15, false) { a, b -> a.pow(b * E) }
// Uses the "$" key
// Same precedence as power
// Right-associative (typical for exponentiation operators)
// 2 $ 3 = 2 ^ (3 * e)

val negFunction = createFunction("neg") { a -> -a }
// Uses the "neg" key
// neg(2) = -2

val max3Function = createFunction("max3") { a, b, c -> max(max(a, b), c) }
// Uses the "max3" key
// max3(2, 3, 1) = 3

val pConstant = createConstant("p", 3.14)
// Uses the "p" key
// p * 2 = 6.28

val customModule = createModule {
    withOperator(atOperator, dollarOperator)
    withFunction(negFunction, max3Function)
    withConstant(pConstant)
}
// Regroup all of our custom elements in a module
```

## Custom context

These elements can then be added to your own custom context.

```kotlin
val customCtx = createDefaultContext {
    // Includes the operations and functions from the default context
    // Now we add our custom elements
    withOperator(atOperator, dollarOperator)
    withFunction(negFunction, max3Function)
    withConstant(pConstant)
}

// Or we can simply include the module that already regroups all of our elements
val customCtx = createDefaultContext {
    include(customModule)
}

// If we want to create a context from scratch (no builtin operations/functions included)
val customCtx = createContext {
    include(customModule)
}
```

If an element with the same key already exists in the context, it will be ignored.

A context can also specify a certain "combiner operator" using the `withCombinerOperator` method.
For example, in the default context, the combiner operator is `*` because when combining two elements
that don't have an operator inbetween, a multiplication is implied: `3a = 3 * a`.