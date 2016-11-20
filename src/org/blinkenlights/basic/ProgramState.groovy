package org.blinkenlights.basic

import org.blinkenlights.basic.statements.Statement

class ProgramState {
    private NavigableMap<Integer, Statement> statements
    private Stack<Integer> stack = []
    private int currentLineNumber;
    private Stack<ForState> forStack = []
    private Map<String, Integer> variables = [:]

    ProgramState(NavigableMap<Integer, Statement> statements) {
        this.statements = statements;
        stack.push(statements.firstEntry().key)
        currentLineNumber = statements.firstEntry().key
    }

    def int end() {
        currentLineNumber = Integer.MAX_VALUE
    }

    def boolean finished() {
        stack.empty() || currentLineNumber == Integer.MAX_VALUE
    }

    def advanceLine() {
        currentLineNumber = statements.higherKey(currentLineNumber)
        if (currentLineNumber == null) {
            currentLineNumber = Integer.MAX_VALUE
        }
    }

    def gotoLine(int targetLine) {
        currentLineNumber = targetLine;
    }

    def gosubToLine(int targetLine) {
        stack.push(currentLineNumber)
        currentLineNumber = targetLine
    }

    def returnFromGosub() {
        currentLineNumber = stack.pop()
        advanceLine()
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
            throw new Exception("FOR and NEXT variable name mismatch.  Expected $currentForState.variableName, got $variableName")
        }
        variables[variableName] = variables[variableName] + 1
        if (variables[variableName] <= currentForState.toValue) {
            currentLineNumber = currentForState.startingLineNumber
        }
        else {
            forStack.pop()
            advanceLine()
        }
    }

    def Integer readVariable(String variableName) {
        variables[variableName]
    }

    def writeVariable(String variableName, Integer value) {
        variables[variableName] = value
    }
}
