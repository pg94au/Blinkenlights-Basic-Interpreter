package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class ReturnStatement implements Statement {
    @Override
    void execute(ProgramState programState) {
        programState.returnFromGosub()
    }
}
