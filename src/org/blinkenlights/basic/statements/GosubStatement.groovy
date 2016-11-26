package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class GosubStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        try {
            interpreter.gosubToLine(targetLineNumber)
        }
        catch (IllegalArgumentException) {
            interpreter.printStream.println("! Target line number $targetLineNumber does not exist")
            interpreter.stop()
        }
    }
}
