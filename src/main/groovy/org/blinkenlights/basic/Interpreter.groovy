package org.blinkenlights.basic

import org.blinkenlights.basic.statements.Statement

class Interpreter {
    private NavigableMap<Integer, Statement> statements
    private Stack<Integer> stack = []
    private int currentLineNumber;
    private Stack<ForState> forStack = []
    private Map<String, Integer> variables = [:]
    private boolean running = false
    BufferedReader inputReader
    PrintStream outputStream
    PrintStream errorStream

    Interpreter(String program) {
        this(program, System.in, System.out, System.err)
    }

    Interpreter(String program, InputStream inputStream, OutputStream outputStream, OutputStream errorStream) {
        this(parseStatementsFromProgramText(program), inputStream, outputStream, errorStream)
    }

    Interpreter(NavigableMap<Integer, Statement> statements, InputStream inputStream, OutputStream outputStream, OutputStream errorStream) {
        this.statements = statements
        currentLineNumber = statements.firstEntry().key
        this.inputReader = inputStream.newReader()
        this.outputStream = new PrintStream(outputStream)
        this.errorStream = new PrintStream(errorStream)
    }

    static NavigableMap<Integer, Statement> parseStatementsFromProgramText(String program) {
        def programStream = new ByteArrayInputStream(program.bytes)
        def statementParser = new StatementParser()
        def statements = statementParser.parse(programStream)
        statements
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
        errorStream.println("! Stopped at line $currentLineNumber")
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
