# Strategy pattern: calculator

Assignment 5, Option B. Java 17.

Nurdaulet Mukhitden | SE-2537

## Build and run

Install a JDK 17 or newer and run from this directory:

```sh
sh run.sh
sh test.sh
```

No Maven, network downloads or external libraries are required. The scripts compile
with `--release 17`, `-Xlint:all` and `-Werror`.

The same `Calculator` instance switches from addition to subtraction,
multiplication and maximum. With operands `12.5` and `2`, the demo prints:

```text
Same Calculator, operands 12.5 and 2:
Addition: 14.5
Subtraction: 10.5
Multiplication: 25.0
Maximum (new strategy): 12.5
```

`BigDecimal` inputs use decimal strings. Addition, subtraction and multiplication
use exact decimal arithmetic; this example has no division or rounding policy.

## Structure

```text
src/main/java/edu/aitu/strategy/  interface, context, strategies and client
src/test/java/edu/aitu/strategy/  executable behavior checks
uml/                            class diagram and editable diagram source
Assignment5_SE-2537_Mukhitden_Nurdaulet.md  required reflection
```

`Calculator` depends only on the `Operation` interface. It does not inspect a
strategy's class, use `instanceof`, or branch on an operation name. The client
chooses the implementation and calls `setOperation` when it wants a different
algorithm. Null strategies and operands are rejected; a failed replacement leaves
the previous strategy in place. The mutable context is intended for a single
thread, which is enough for this console demonstration.

## Add a strategy

1. Create a class implementing `Operation` with an `apply` method.
2. Supply it to `new Calculator(...)` or `calculator.setOperation(...)` in a client.
3. Add behavior checks for the new algorithm.

`MaximumStrategy` is the concrete extension example. Its commit leaves
`Calculator`, `Operation` and all three arithmetic strategies unchanged.

## Verification

`sh test.sh` runs 13 checks covering decimal arithmetic, negative values, zero,
runtime switching, extension through the interface and invalid inputs. Tests use
numeric comparison, so numerically equal decimals such as `4` and `4.00` compare
equally.
