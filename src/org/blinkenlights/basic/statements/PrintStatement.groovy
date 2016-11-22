package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class PrintStatement implements Statement {
    PrintArgument[] printArguments

    @Override
    void execute(ProgramState programState) {
        printArguments.each {
            print it.toString(programState)
        }
        println()
        programState.advanceLine()
    }
}
