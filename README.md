# Fractions
Fractions for java

## Autoexpanding
When a LongFraction overflows, it first attempts to simplify itself, if that fails then the fraction is too large to fit in a long/long fraction, so instead it uses BigFraction, which is BigInteger/BigInteger
### Autoshrinking
When a BigFraction's value can be stored in a LongFraction, it does so

## Brrrr
Fractions lazily simplify, so it's performance is much faster than autosimplification, and there's a small cache of common numbers
