package org.blinkenlights.basic

import org.blinkenlights.basic.statements.Statement

class Interpreter {
    def executeProgram(NavigableMap<Integer, Statement> statements) {
        def programState = new ProgramState(statements);

        while (!programState.finished()) {
            def statement = statements.get(programState._currentLineNumber);

            if (statement != null) {
                statement.execute(programState);
            }
        }
    }
}
