package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class NextStatement implements Statement {
    String variableName

    @Override
    void execute(Interpreter interpreter) {
        def forState = interpreter.popForLoop()
        if (variableName != forState.variableName) {
            interpreter.printStream.println("! FOR and NEXT variable name mismatch.  Expected $forState.variableName, got $variableName")
            interpreter.stop()
            return
        }

        def loopValue = interpreter.readVariable(variableName)
        loopValue++
        interpreter.writeVariable(variableName, loopValue)

        if (loopValue <= forState.limit) {
            interpreter.pushForLoop(forState)
            interpreter.gotoLine(forState.startingLineNumber)
        }
        else {
            interpreter.advanceLine()
        }
    }
}
