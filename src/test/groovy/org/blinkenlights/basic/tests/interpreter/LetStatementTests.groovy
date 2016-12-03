package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class LetStatementTests extends Specification {
    def "Can set literal number value to variable"() {
        setup:
        def statements = """
10 LET X = 123
"""
        def interpreter = new Interpreter(statements)
        when:
        interpreter.executeProgram()
        then:
        interpreter.readVariable("X") == 123
    }

    def "Can re-assign a new value to a variable"() {
        setup:
        def statements = """
10 LET X = 123
20 LET X = 234
"""
        def interpreter = new Interpreter(statements)
        when:
        interpreter.executeProgram()
        then:
        interpreter.readVariable("X") == 234
    }

    def "Can assign result of an expression to a variable"() {
        setup:
        def statements = """
10 LET X = 12 + 23
"""
        def interpreter = new Interpreter(statements)
        when:
        interpreter.executeProgram()
        then:
        interpreter.readVariable("X") == 35
    }

    def "Can assign one variable to another"() {
        setup:
        def statements = """
10 LET X = 123
20 LET Y = X
"""
        def interpreter = new Interpreter(statements)
        when:
        interpreter.executeProgram()
        then:
        interpreter.readVariable("Y") == 123
    }
}
