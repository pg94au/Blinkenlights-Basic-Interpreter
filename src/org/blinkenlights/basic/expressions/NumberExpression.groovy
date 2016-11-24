package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.Interpreter

class NumberExpression implements Expression {
    Integer value

    @Override
    Integer calculate(Interpreter interpreter) {
        value
    }
}
