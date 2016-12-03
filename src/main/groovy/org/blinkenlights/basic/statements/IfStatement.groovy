package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter
import org.blinkenlights.basic.equations.Equation

class IfStatement implements Statement {
    Equation equation
    int targetLineNumber

    @Override
    void execute(Interpreter interpreter) {
        if (equation.solve(interpreter)) {
            interpreter.gotoLine(targetLineNumber)
        }
        else {
            interpreter.advanceLine()
        }
    }
}
