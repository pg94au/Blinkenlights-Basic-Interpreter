package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class PrintStatement implements Statement {
    String arg

    @Override
    void execute(ProgramState programState) {
        println arg
        programState.advanceLine()
    }
}
