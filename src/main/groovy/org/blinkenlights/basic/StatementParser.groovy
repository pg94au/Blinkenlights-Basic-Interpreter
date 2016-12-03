package org.blinkenlights.basic

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.blinkenlights.basic.gen.BasicLexer
import org.blinkenlights.basic.gen.BasicParser
import org.blinkenlights.basic.statements.Statement
import org.blinkenlights.basic.visitors.ProgramVisitor

class StatementParser {
    NavigableMap<Integer, Statement> parse(InputStream inputStream) {
        def input = new ANTLRInputStream(inputStream)
        def lexer = new BasicLexer(input)
        def tokens = new CommonTokenStream(lexer)
        def parser = new BasicParser(tokens)
        def tree = parser.program()

        def visitor = new ProgramVisitor()
        visitor.visit(tree)

        def statements = visitor.getStatements()

        statements
    }
}
