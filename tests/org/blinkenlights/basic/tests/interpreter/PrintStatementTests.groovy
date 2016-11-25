package org.blinkenlights.basic.tests.interpreter

import org.blinkenlights.basic.Interpreter
import spock.lang.Specification

class PrintStatementTests extends Specification {
    def ExecuteProgram(statements, outputStream) {
        def interpreter = new Interpreter(statements, outputStream)
        interpreter.executeProgram()
    }

    def "Can print literal string"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        ExecuteProgram("""
            10 PRINT "HELLO"
            20 END
            """, new PrintStream(outputStream))
        then:
        new String(outputStream.toByteArray()) == "HELLO" + System.getProperty("line.separator")
    }

    def "Can print expression"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        ExecuteProgram("""
            10 PRINT 12 + 23
            20 END
            """, new PrintStream(outputStream))
        then:
        new String(outputStream.toByteArray()) == "35" + System.getProperty("line.separator")
    }

    def "Comma includes a space between printed arguments"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        ExecuteProgram("""
            10 PRINT "Hello", "there"
            20 END
            """, new PrintStream(outputStream))
        then:
        new String(outputStream.toByteArray()) == "Hello there" + System.getProperty("line.separator")
    }

    def "Semicolon directly appends printed arguments"() {
        setup:
        def outputStream = new ByteArrayOutputStream()
        when:
        ExecuteProgram("""
            10 PRINT "Hello"; "there"
            20 END
            """, new PrintStream(outputStream))
        then:
        new String(outputStream.toByteArray()) == "Hellothere" + System.getProperty("line.separator")
    }
}
