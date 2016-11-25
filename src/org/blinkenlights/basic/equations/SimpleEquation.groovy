package org.blinkenlights.basic.equations

import org.blinkenlights.basic.Interpreter
import org.blinkenlights.basic.expressions.Expression

abstract class SimpleEquation implements Equation {
    Expression left
    Expression right
    def leftValue
    def rightValue

    @Override
    boolean solve(Interpreter interpreter) {
        leftValue = left.calculate(interpreter)
        rightValue = right.calculate(interpreter)

        solve()
    }

    abstract boolean solve()
}