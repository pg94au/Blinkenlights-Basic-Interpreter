package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class LetStatementTests extends Specification {
    def "Can set literal number value to variable"() {
        setup:
        def statements = "10 LET X = 123\r\n"
        def interpreter = new Interpreter(statements)
        when:
        interpreter.executeProgram()
        then:
        interpreter.readVariable("X") == 123
    }
}
