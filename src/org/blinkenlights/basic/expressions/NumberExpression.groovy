package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.ProgramState

class NumberExpression implements Expression {
    Integer value

    @Override
    Integer calculate(ProgramState programState) {
        value
    }
}
