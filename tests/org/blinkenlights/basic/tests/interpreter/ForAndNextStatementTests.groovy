package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class ForAndNextStatementTests extends Specification {
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

    def "Can construct loop that repeats statements"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=0
            20 FOR I = 0 TO 5
            30 LET X = I
            40 NEXT I
            50 END
            """)
        then:
        with (interpreter.variables) {
            X == 5
        }
    }

    def "A loop will always execute at least once"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=0
            20 FOR I = 5 TO 5
            30 LET X = I
            40 NEXT I
            50 END
            """)
        then:
        with (interpreter.variables) {
            X == 5
        }
    }

    def "Mismatched next statement stops execution and prints error"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        def interpreter = ExecuteProgram("""
            10 LET X = 1 
            20 FOR Y = 1 TO 5
            30 LET X = X + 1
            40 NEXT Z
            50 LET X = 123
            60 END
            """, new PrintStream(outputStream))
        then:
        with (interpreter.variables) {
            X == 2
        }
        outputStream.toByteArray().length > 0
    }
}
