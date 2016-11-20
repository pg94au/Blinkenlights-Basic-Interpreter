package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

class ForStatement implements Statement {
    String variableName
    int fromValue
    int toValue

    @Override
    void execute(ProgramState programState) {
        programState.startFor(variableName, fromValue, toValue)
    }
}
