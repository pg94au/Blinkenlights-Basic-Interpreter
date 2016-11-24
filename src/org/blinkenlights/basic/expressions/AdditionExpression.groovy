package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.Interpreter

class AdditionExpression implements Expression {
    Expression left
    Expression right

    @Override
    Integer calculate(Interpreter interpreter) {
        left.calculate(interpreter) + right.calculate(interpreter)
    }
}
