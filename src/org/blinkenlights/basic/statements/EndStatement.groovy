package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class EndStatement implements Statement {
    @Override
    void execute(ProgramState programState) {
        programState.end()
    }
}
