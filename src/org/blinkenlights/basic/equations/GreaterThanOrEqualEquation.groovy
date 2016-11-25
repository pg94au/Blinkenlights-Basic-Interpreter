package org.blinkenlights.basic.equations

class GreaterThanOrEqualEquation extends SimpleEquation {
    @Override
    boolean solve() {
        leftValue >= rightValue
    }
}
