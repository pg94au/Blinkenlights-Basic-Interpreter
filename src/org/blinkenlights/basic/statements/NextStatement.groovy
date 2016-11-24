package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class NextStatement implements Statement {
    String variableName

    @Override
    void execute(Interpreter interpreter) {
        interpreter.nextFor(variableName)
    }
}
