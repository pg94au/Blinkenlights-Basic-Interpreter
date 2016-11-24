package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class GotoStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        interpreter.gotoLine(targetLineNumber)
    }
}
