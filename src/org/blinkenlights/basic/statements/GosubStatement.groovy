package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class GosubStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(ProgramState programState) {
        programState.gosubToLine(targetLineNumber)
    }
}
