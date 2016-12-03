package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

interface Statement {
    void execute(Interpreter interpreter);
}
