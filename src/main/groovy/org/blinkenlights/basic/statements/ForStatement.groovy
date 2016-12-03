package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class ForStatement implements Statement {
    String variableName
    int fromValue
    int toValue

    @Override
    void execute(Interpreter interpreter) {
        interpreter.writeVariable(variableName, fromValue)
        interpreter.advanceLine()
        def forState = interpreter.createForState(variableName, toValue)
        interpreter.pushForLoop(forState)
    }
}
