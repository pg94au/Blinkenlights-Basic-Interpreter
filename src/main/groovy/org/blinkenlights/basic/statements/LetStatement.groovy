package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter
import org.blinkenlights.basic.expressions.Expression

class LetStatement implements Statement {
    String variableName
    Expression expression

    @Override
    void execute(Interpreter interpreter) {
        def result = expression.calculate(interpreter)
        interpreter.writeVariable(variableName, result)
        interpreter.advanceLine()
    }
}
