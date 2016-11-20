package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState

interface Statement {
    void execute(ProgramState programState);
}
