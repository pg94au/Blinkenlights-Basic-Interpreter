package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class PrintStatement implements Statement {
    String variableName

    @Override
    void execute(ProgramState programState) {
        println programState.readVariable(variableName)
    }
}
