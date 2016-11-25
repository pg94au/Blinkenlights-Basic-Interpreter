package org.blinkenlights.basic.equations

import org.blinkenlights.basic.Interpreter

interface Equation {
    boolean solve(Interpreter interpreter)
}
