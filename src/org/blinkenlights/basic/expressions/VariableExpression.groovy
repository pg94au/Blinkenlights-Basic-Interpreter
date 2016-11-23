package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.ProgramState

class VariableExpression implements Expression {
    String variableName

    @Override
    Integer calculate(ProgramState programState) {
        programState.readVariable(variableName)
    }
}
