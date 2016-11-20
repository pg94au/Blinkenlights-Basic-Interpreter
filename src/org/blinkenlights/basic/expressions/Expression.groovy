package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.ProgramState

interface Expression {
    Integer calculate(ProgramState programState)
}
