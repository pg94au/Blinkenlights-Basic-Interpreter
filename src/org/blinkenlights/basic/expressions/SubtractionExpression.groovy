package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.ProgramState

class SubtractionExpression implements Expression {
    Expression left
    Expression right

    @Override
    Integer calculate(ProgramState programState) {
        left.calculate(programState) - right.calculate(programState)
    }
}
