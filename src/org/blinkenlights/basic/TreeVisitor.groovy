package org.blinkenlights.basic

import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser
import org.blinkenlights.basic.statements.EndStatement
import org.blinkenlights.basic.statements.ForStatement
import org.blinkenlights.basic.statements.GosubStatement
import org.blinkenlights.basic.statements.GotoStatement
import org.blinkenlights.basic.statements.LetStatement
import org.blinkenlights.basic.statements.NextStatement
import org.blinkenlights.basic.statements.PrintArgument
import org.blinkenlights.basic.statements.PrintStatement
import org.blinkenlights.basic.statements.ReturnStatement
import org.blinkenlights.basic.statements.Statement

class TreeVisitor extends BasicBaseVisitor<String> {
    def statements = new TreeMap<Integer, Statement>()
    int currentLineNumber

    @Override
    String visitLineNum(BasicParser.LineNumContext ctx) {
        currentLineNumber = Integer.parseInt(ctx.INT().toString())

        super.visitLineNum(ctx)
    }

    @Override
    String visitEndStatement(BasicParser.EndStatementContext ctx) {
        def endStatement = new EndStatement()
        statements[currentLineNumber] = endStatement

        super.visitEndStatement(ctx)
    }

    @Override
    String visitForStatement(BasicParser.ForStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def fromValue = Integer.parseInt(ctx.INT(0).toString())
        def toValue = Integer.parseInt(ctx.INT(1).toString())

        def forStatement = new ForStatement(variableName: variableName, fromValue: fromValue, toValue: toValue)
        statements[currentLineNumber] = forStatement

        super.visitForStatement(ctx)
    }

    @Override
    String visitGosubStatement(BasicParser.GosubStatementContext ctx) {
        def targetLineNumber = Integer.parseInt(ctx.INT().toString())
        def gosubStatement = new GosubStatement(targetLineNumber: targetLineNumber)
        statements[currentLineNumber] = gosubStatement

        super.visitGosubStatement(ctx)
    }

    @Override
    String visitGotoStatement(BasicParser.GotoStatementContext ctx) {
        def targetLineNumber = Integer.parseInt(ctx.INT().toString())
        def gotoStatement = new GotoStatement(targetLineNumber: targetLineNumber)
        statements[currentLineNumber] = gotoStatement

        super.visitGotoStatement(ctx)
    }

    @Override
    String visitLetStatement(BasicParser.LetStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def expressionVisitor = new ExpressionVisitor()
        def expression = expressionVisitor.visit(ctx.expression())
        def letStatement = new LetStatement(variableName: variableName, expression: expression)
        statements[currentLineNumber] = letStatement

        super.visitLetStatement(ctx)
    }

    @Override
    String visitNextStatement(BasicParser.NextStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def nextStatement = new NextStatement(variableName: variableName)
        statements[currentLineNumber] = nextStatement

        super.visitNextStatement(ctx)
    }

    @Override
    String visitPrintQuotedString(BasicParser.PrintQuotedStringContext ctx) {
        def quotedString = ctx.QUOTED_STRING().text
        quotedString = quotedString[1..-2]
        def printArgument = new PrintArgument(quotedString)
        def printStatement = new PrintStatement(printArgument: printArgument)
        statements[currentLineNumber] = printStatement

        return super.visitPrintQuotedString(ctx)
    }

    @Override
    String visitPrintExpression(BasicParser.PrintExpressionContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def expression = expressionVisitor.visit(ctx.expression())
        def printArgument = new PrintArgument(expression)
        def printStatement = new PrintStatement(printArgument: printArgument)
        statements[currentLineNumber] = printStatement

        return super.visitPrintExpression(ctx)
    }

    @Override
    String visitReturnStatement(BasicParser.ReturnStatementContext ctx) {
        def returnStatement = new ReturnStatement()
        statements[currentLineNumber] = returnStatement

        super.visitReturnStatement(ctx)
    }
}
