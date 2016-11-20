package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState
import org.blinkenlights.basic.expressions.Expression

class LetStatement implements Statement {
    String variableName
    Expression expression

    @Override
    void execute(ProgramState programState) {
        def result = expression.calculate(programState)
        programState.writeVariable(variableName, result)
        programState.advanceLine()
    }
}
