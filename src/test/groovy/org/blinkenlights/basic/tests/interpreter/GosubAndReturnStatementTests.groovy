package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class GosubAndReturnStatementTests extends Specification {
    def ExecuteProgram(statements) {
        def interpreter = new Interpreter(statements)
        interpreter.executeProgram()
        interpreter
    }

    def ExecuteProgram(statements, errorStream) {
        def interpreter = new Interpreter(statements, System.in, new ByteArrayOutputStream(), errorStream)
        interpreter.executeProgram()
        interpreter
    }

    def "Can go to another line and then return"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 GOSUB 40
            30 END
            40 LET X=500
            50 RETURN
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Calling to non-existent line stops execution and prints an error"() {
        setup:
        def errorStream = new ByteArrayOutputStream()
        when:
        def interpreter = ExecuteProgram("""
            10 LET X = 1 
            20 GOSUB 50
            30 LET X = 123
            40 END
            """, new PrintStream(errorStream))
        then:
        with (interpreter.variables) {
            X == 1
        }
        errorStream.toByteArray().length > 0
    }

    def "Return without gosub stops execution and prints an error"() {
        setup:
        def errorStream = new ByteArrayOutputStream()
        when:
        def interpreter = ExecuteProgram("""
            10 LET X = 1
            20 RETURN
            30 LET X = 123
            40 END
            """, new PrintStream(errorStream))
        then:
        with (interpreter.variables) {
            X == 1
        }
        errorStream.toByteArray().length > 0
    }
}
