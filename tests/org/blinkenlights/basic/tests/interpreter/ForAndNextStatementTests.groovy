package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class ForAndNextStatementTests extends Specification {
    def ExecuteProgram(statements) {
        def interpreter = new Interpreter(statements)
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
}
