package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class PrintStatement implements Statement {
    PrintArgument[] printArguments

    @Override
    void execute(Interpreter interpreter) {
        printArguments.each {
            print it.toString(interpreter)
        }
        println()
        interpreter.advanceLine()
    }
}
