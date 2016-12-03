package org.blinkenlights.basic.equations

class DoesNotEqualEquation extends SimpleEquation {
    @Override
    boolean solve() {
        leftValue != rightValue
    }
}
