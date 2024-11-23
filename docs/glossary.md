### Operator

* Element that represents an operation between the 2 elements next to it
* Represented by the interface `KthOperator`
* Example: **`+`** (`a + b`), **`/`** (`a / b`)
* Can be created using the methods `createOperator` and `asOperator`
* Can be included in a module or a context using `withOperation`

### Function

* Element that calculates a value based on a certain number of parameters
* Represented by the interface `KthFunction`
* Example: **`sin`** (`sin(a)`), **`max`** (`max(a, b)`)
* Can be created using the methods `createFunction` and `asFunction`
* Can be included in a module or a context using `withFunction`

### Constant

* Element that represents a constant value
* Represented by the interface `KthConstant`
* Example: **`pi`** (`2 pi r`), **`e`** (`e^x`)
* Can be created using the methods `createConstant` and `asConstant`
* Can be included in a module or a context using `withConstant`

### Module

* Group of operators, functions and constants
* Represented by the interface `KthModule`
* Can be created using the method `buildModule`
* Can be included in a module or a context using `include`

### Context

* Object that defines all the operators, functions and constants that can be used
* Represented by the interface `KthContext`
* Can be created using the methods `buildContext` and `buildDefaultContext`

### Expression

* Arithmetic expression that has been parsed into a sorted list of tokens
* Can be used to calculate a result
* Represented by the interface `KthExpression`