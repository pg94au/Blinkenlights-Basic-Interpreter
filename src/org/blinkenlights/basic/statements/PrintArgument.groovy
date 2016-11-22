package org.blinkenlights.basic.statements

import org.blinkenlights.basic.ProgramState
import org.blinkenlights.basic.expressions.Expression

class PrintArgument {
    String quotedString
    Expression expression

    PrintArgument(String quotedString) {
        this.quotedString = quotedString
    }

    PrintArgument(Expression expression) {
        this.expression = expression
    }

    String toString(ProgramState programState) {
        if (quotedString != null) {
            quotedString
        }
        else {
            expression.calculate(programState)
        }
    }
}
