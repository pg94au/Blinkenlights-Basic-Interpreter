package org.blinkenlights.basic.statements

import org.blinkenlights.basic.Interpreter

class InputStatement implements Statement {
    String variableName;

    @Override
    void execute(Interpreter interpreter) {
        interpreter.printStream.print("? ")
        def input = interpreter.inputReader.readLine()
        def value = Integer.parseInt(input)
        interpreter.writeVariable(variableName, value)
        interpreter.advanceLine()
    }
}
