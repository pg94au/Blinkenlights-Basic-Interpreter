package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState
import org.blinkenlights.basic.expressions.Expression

class PrintArgument {
    String string
    Expression expression

    PrintArgument(String string) {
        this.string = string
    }

    PrintArgument(Expression expression) {
        this.expression = expression
    }

    String toString(ProgramState programState) {
        if (string != null) {
            string
        }
        else {
            expression.calculate(programState)
        }
    }
}
