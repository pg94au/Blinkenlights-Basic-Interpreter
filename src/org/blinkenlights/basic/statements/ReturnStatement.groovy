package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class ReturnStatement implements Statement {
    @Override
    void execute(Interpreter interpreter) {
        try {
            interpreter.returnFromGosub()
        }
        catch (IllegalStateException) {
            interpreter.printStream.println("! Call stack empty")
            interpreter.stop()
        }
    }
}
