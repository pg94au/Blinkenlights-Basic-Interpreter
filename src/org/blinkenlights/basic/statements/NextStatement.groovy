package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class NextStatement implements Statement {
    String variableName

    @Override
    void execute(ProgramState programState) {
        programState.nextFor(variableName)
    }
}
