package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState
import org.blinkenlights.basic.expressions.Expression

class PrintArgument {
    String string
    Expression expression
    String variableName

    String toString(ProgramState programState) {
        if (string != null) {
            string
        }
        else if (expression != null) {
            expression.calculate(programState)
        }
        else {
            programState.readVariable(variableName)
        }
    }
}
