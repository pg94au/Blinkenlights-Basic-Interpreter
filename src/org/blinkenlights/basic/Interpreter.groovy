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

    def startFor(String variableName, int fromValue, int toValue) {
        def startingLineNumber = statements.higherKey(currentLineNumber)
        def forState = new ForState(variableName: variableName, toValue: toValue, startingLineNumber: startingLineNumber)
        forStack.push(forState)
        variables[variableName] = fromValue
        advanceLine()
    }

    def nextFor(String variableName) {
        def currentForState = forStack.peek()
        if (currentForState.variableName != variableName) {
            printStream.println("! FOR and NEXT variable name mismatch.  Expected $currentForState.variableName, got $variableName")
            stop()
        }
        else {
            variables[variableName] = variables[variableName] + 1
            if (variables[variableName] <= currentForState.toValue) {
                currentLineNumber = currentForState.startingLineNumber
            }
            else {
                forStack.pop()
                advanceLine()
            }
        }
    }

    Integer readVariable(String variableName) {
        variables[variableName]
    }

    def writeVariable(String variableName, Integer value) {
        variables[variableName] = value
    }
}
