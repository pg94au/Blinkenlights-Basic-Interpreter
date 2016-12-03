package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter
import org.blinkenlights.basic.expressions.Expression

class PrintArgument {
    String string
    Expression expression
    String variableName

    String toString(Interpreter interpreter) {
        if (string != null) {
            string
        }
        else if (expression != null) {
            expression.calculate(interpreter)
        }
        else {
            interpreter.readVariable(variableName)
        }
    }
}
