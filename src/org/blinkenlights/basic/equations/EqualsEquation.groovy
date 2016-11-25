package org.blinkenlights.basic.equations

class EqualsEquation extends SimpleEquation {
    @Override
    boolean solve() {
        leftValue == rightValue
    }
}
