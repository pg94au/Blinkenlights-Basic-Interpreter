package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class PrintStatement implements Statement {
    PrintArgument[] printArguments

    @Override
    void execute(Interpreter interpreter) {
        printArguments.each { arg ->
            interpreter.outputStream.print(arg.toString(interpreter))
        }
        interpreter.outputStream.println()
        interpreter.advanceLine()
    }
}
