package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class EndStatement implements Statement {
    @Override
    void execute(Interpreter interpreter) {
        interpreter.end()
    }
}
