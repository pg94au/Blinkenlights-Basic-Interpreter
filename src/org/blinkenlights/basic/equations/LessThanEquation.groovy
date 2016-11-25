package org.blinkenlights.basic.equations

class LessThanEquation extends SimpleEquation {
    @Override
    boolean solve() {
        leftValue < rightValue
    }
}
