package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class GotoStatementTests extends Specification {
    def ExecuteProgram(statements) {
        def interpreter = new Interpreter(statements)
        interpreter.executeProgram()
        interpreter
    }

    def ExecuteProgram(statements, outputStream) {
        def interpreter = new Interpreter(statements, System.in, new ByteArrayOutputStream(), outputStream)
        interpreter.executeProgram()
        interpreter
    }

    def "Can jump to another line"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 GOTO 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Jumping to non-existent line stops execution and prints an error"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        def interpreter = ExecuteProgram("""
            10 LET X = 1 
            20 GOTO 50
            30 LET X = 123
            40 END
            """, new PrintStream(outputStream))
        then:
        with (interpreter.variables) {
            X == 1
        }
        outputStream.toByteArray().length > 0
    }
}
