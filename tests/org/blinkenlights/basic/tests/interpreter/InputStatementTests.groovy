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

    def "Can input a value from input stream and store in a variable"() {
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

    def "Can call input more than once"() {
        setup:
        def inputStream = new ByteArrayInputStream("123\r\n234".bytes)
        when:
        def interpreter = ExecuteProgram("""
            10 INPUT X
            20 INPUT Y
            """, inputStream)
        then:
        with (interpreter.variables) {
            X == 123
            Y == 234
        }
    }
}
