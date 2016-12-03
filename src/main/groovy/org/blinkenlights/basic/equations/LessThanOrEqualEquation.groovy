package org.blinkenlights.basic.equations

class LessThanOrEqualEquation extends SimpleEquation {
    @Override
    boolean solve() {
        leftValue <= rightValue
    }
}
