package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class GotoStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        try {
            interpreter.gotoLine(targetLineNumber)
        }
        catch (IllegalArgumentException) {
            interpreter.outputStream.println("! Target line number $targetLineNumber does not exist")
            interpreter.stop()
        }
    }
}
