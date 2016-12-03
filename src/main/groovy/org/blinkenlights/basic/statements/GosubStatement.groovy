package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class GosubStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        try {
            interpreter.pushLineNumber()
            interpreter.gotoLine(targetLineNumber)
        }
        catch (IllegalArgumentException) {
            interpreter.printStream.println("! Target line number $targetLineNumber does not exist")
            interpreter.stop()
        }
    }
}
