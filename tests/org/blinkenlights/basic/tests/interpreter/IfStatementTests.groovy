package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class IfStatementTests extends Specification {
    def ExecuteProgram(statements) {
        def interpreter = new Interpreter(statements)
        interpreter.executeProgram()
        interpreter
    }

    def "Branches when two literal values that are equal are tested for equality"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 == 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Branches when two equations that are equal are tested for equality"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF X + 50 == (50 * 3) * 2 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch when unequal literal values are tested for equality"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 == 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Branches when unequal literal values are tested for inequality"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 != 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch when equal literal values are tested for inequality"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 != 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Branches on greater than test where first literal value greater than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 321 > 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch on greater than test where first literal value less than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 > 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Branches on less than test where first literal value less than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 < 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch on less than test where first literal value greater than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 321 < 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Branches on greater than or equal test where first literal value greater than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 321 >= 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Branches on greater than or equal test where first literal value equal to second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 >= 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch on greater than or equal test where first literal value less than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 >= 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }

    def "Branches on less than or equal test where first literal value less than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 <= 321 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Branches on less than or equal test where first literal value equal to second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 123 <= 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 250
        }
    }

    def "Does not branch on less than or equal test where first literal value greater than second"() {
        when:
        def interpreter = ExecuteProgram("""
            10 LET X=250
            20 IF 321 <= 123 THEN 40
            30 LET X=500
            40 END
            """)
        then:
        with (interpreter.variables) {
            X == 500
        }
    }
}
