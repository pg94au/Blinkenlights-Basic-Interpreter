package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class PrintStatement implements Statement {
    PrintArgument printArgument

    @Override
    void execute(ProgramState programState) {
        println printArgument.toString(programState)
        programState.advanceLine()
    }
}
