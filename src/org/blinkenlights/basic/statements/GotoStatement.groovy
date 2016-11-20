package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class GotoStatement implements Statement {
    int targetLineNumber

    @Override
    void execute(ProgramState programState) {
        programState.gotoLine(targetLineNumber)
    }
}
