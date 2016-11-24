package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class ForStatement implements Statement {
    String variableName
    int fromValue
    int toValue

    @Override
    void execute(Interpreter interpreter) {
        interpreter.startFor(variableName, fromValue, toValue)
    }
}
