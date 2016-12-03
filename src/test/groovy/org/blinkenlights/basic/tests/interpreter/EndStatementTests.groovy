package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class EndStatementTests extends Specification {
    def ExecuteProgram(statements) {
        def interpreter = new Interpreter(statements)
        interpreter.executeProgram()
        interpreter
    }

    def "End stops execution of a running application"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=0
            20 END
            30 LET X=1
            """)
        then:
        with (interpreter.variables) {
            X == 0
        }
    }
}
