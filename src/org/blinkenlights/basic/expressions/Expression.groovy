package org.blinkenlights.basic.expressions

import org.blinkenlights.basic.Interpreter

interface Expression {
    Integer calculate(Interpreter interpreter)
}
