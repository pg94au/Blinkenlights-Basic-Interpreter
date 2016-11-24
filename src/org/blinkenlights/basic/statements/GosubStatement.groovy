package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class GosubStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        interpreter.gosubToLine(targetLineNumber)
    }
}
