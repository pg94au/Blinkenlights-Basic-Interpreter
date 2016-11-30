package org.blinkenlights.basic.tests.interpreter

import com.sun.xml.internal.ws.util.ByteArrayBuffer
import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class InputStatementTests extends Specification {
    def ExecuteProgram(statements, inputStream) {
        def interpreter = new Interpreter(statements, inputStream, new ByteArrayOutputStream(), new ByteArrayOutputStream())
        interpreter.executeProgram()
        interpreter
    }

    def "Branches when two literal values that are equal are tested for equality"() {
        setup:
        def inputStream = new ByteArrayInputStream("123".bytes)
        when:
        def interpreter = ExecuteProgram("""
            10 INPUT X
            """, inputStream)
        then:
        with (interpreter.variables) {
            X == 123
        }
    }
}
