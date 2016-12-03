package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.Interpreter

class VariableExpression implements Expression {
    String variableName

    @Override
    Integer calculate(Interpreter interpreter) {
        interpreter.readVariable(variableName)
    }
}
