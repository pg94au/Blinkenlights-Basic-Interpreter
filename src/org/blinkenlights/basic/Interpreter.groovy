package org.blinkenlights.basic

import org.blinkenlights.basic.statements.Statement

class Interpreter {
    private NavigableMap<Integer, Statement> statements
    private Stack<Integer> stack = []
    private int currentLineNumber;
    private Stack<ForState> forStack = []
    private Map<String, Integer> variables = [:]
    InputStream inputStream
    PrintStream printStream
    boolean running = false

    Interpreter(String program) {
        this(program, System.in, System.out)
    }

    Interpreter(String program, InputStream inputStream, OutputStream outputStream) {
        def programStream = new ByteArrayInputStream(program.bytes)
        def statementParser = new StatementParser()
        statements = statementParser.parse(programStream)
        currentLineNumber = statements.firstEntry().key
        this.inputStream = inputStream
        this.printStream = new PrintStream(outputStream)
    }

    def executeProgram() {
        running = true

        while (running && !finished()) {
            def statement = statements.get(currentLineNumber);

            if (statement != null) {
                statement.execute(this);
            }
        }
    }

    def stop() {
        printStream.println("! Stopped at line $currentLineNumber")
        running = false
    }

    int end() {
        currentLineNumber = Integer.MAX_VALUE
    }

    boolean finished() {
        currentLineNumber == Integer.MAX_VALUE
    }

    def advanceLine() {
        if (statements.higherKey(currentLineNumber) != null) {
            currentLineNumber = statements.higherKey(currentLineNumber)
        }
        else {
            currentLineNumber = Integer.MAX_VALUE
        }
    }

    def gotoLine(int targetLine) {
        if (statements.containsKey(targetLine)) {
            currentLineNumber = targetLine;
        }
        else {
            throw new IllegalArgumentException()
        }
    }

    def popLineNumber() {
        if (!stack.empty()) {
            currentLineNumber = stack.pop()
        }
        else {
            throw new IllegalStateException()
        }
    }

    def pushLineNumber() {
        stack.push(currentLineNumber)
    }

    def createForState(String variableName, int limit) {
        new ForState(variableName: variableName, limit: limit, startingLineNumber: currentLineNumber)
    }

    def pushForLoop(ForState forState) {
        forStack.push(forState)
    }

    def popForLoop() {
        forStack.pop()
    }

    Integer readVariable(String variableName) {
        variables[variableName]
    }

    def writeVariable(String variableName, Integer value) {
        variables[variableName] = value
    }
}
