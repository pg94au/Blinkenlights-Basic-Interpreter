package org.blinkenlights.basic

import org.blinkenlights.basic.expressions.AdditionExpression
import org.blinkenlights.basic.expressions.DivisionExpression
import org.blinkenlights.basic.expressions.Expression
import org.blinkenlights.basic.expressions.MultiplicationExpression
import org.blinkenlights.basic.expressions.NumberExpression
import org.blinkenlights.basic.expressions.SubtractionExpression
import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser
import org.blinkenlights.basic.statements.EndStatement
import org.blinkenlights.basic.statements.ForStatement
import org.blinkenlights.basic.statements.GosubStatement
import org.blinkenlights.basic.statements.GotoStatement
import org.blinkenlights.basic.statements.LetStatement
import org.blinkenlights.basic.statements.NextStatement
import org.blinkenlights.basic.statements.PrintStatement
import org.blinkenlights.basic.statements.ReturnStatement
import org.blinkenlights.basic.statements.Statement

class TreeVisitor extends BasicBaseVisitor<Expression> {
    def statements = new TreeMap<Integer, Statement>()
    int currentLineNumber

    @Override
    Expression visitLineNum(BasicParser.LineNumContext ctx) {
        currentLineNumber = Integer.parseInt(ctx.INT().toString())

        super.visitLineNum(ctx)
    }

    @Override
    Expression visitEndStatement(BasicParser.EndStatementContext ctx) {
        def endStatement = new EndStatement()
        statements[currentLineNumber] = endStatement

        super.visitEndStatement(ctx)
    }

    @Override
    Expression visitForStatement(BasicParser.ForStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def fromValue = Integer.parseInt(ctx.INT(0).toString())
        def toValue = Integer.parseInt(ctx.INT(1).toString())

        def forStatement = new ForStatement(variableName: variableName, fromValue: fromValue, toValue: toValue)
        statements[currentLineNumber] = forStatement

        super.visitForStatement(ctx)
    }

    @Override
    Expression visitGosubStatement(BasicParser.GosubStatementContext ctx) {
        def targetLineNumber = Integer.parseInt(ctx.INT().toString())
        def gosubStatement = new GosubStatement(targetLineNumber: targetLineNumber)
        statements[currentLineNumber] = gosubStatement

        super.visitGosubStatement(ctx)
    }

    @Override
    Expression visitGotoStatement(BasicParser.GotoStatementContext ctx) {
        def targetLineNumber = Integer.parseInt(ctx.INT().toString())
        def gotoStatement = new GotoStatement(targetLineNumber: targetLineNumber)
        statements[currentLineNumber] = gotoStatement

        super.visitGotoStatement(ctx)
    }

    @Override
    Expression visitLetStatement(BasicParser.LetStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def expression = visit(ctx.expression())
        def letStatement = new LetStatement(variableName: variableName, expression: expression)
        statements[currentLineNumber] = letStatement

        super.visitLetStatement(ctx)
    }

    @Override
    Expression visitNextStatement(BasicParser.NextStatementContext ctx) {
        def variableName = ctx.VARNAME().toString()
        def nextStatement = new NextStatement(variableName: variableName)
        statements[currentLineNumber] = nextStatement

        super.visitNextStatement(ctx)
    }

    @Override
    Expression visitPrintStatement(BasicParser.PrintStatementContext ctx) {
        def arg = ctx.arg().getText()
        def printStatement = new PrintStatement(arg: arg)
        statements[currentLineNumber] = printStatement

        super.visitPrintStatement(ctx)
    }

    @Override
    Expression visitReturnStatement(BasicParser.ReturnStatementContext ctx) {
        def returnStatement = new ReturnStatement()
        statements[currentLineNumber] = returnStatement

        super.visitReturnStatement(ctx)
    }

    @Override
    Expression visitValue(BasicParser.ValueContext ctx) {
        new NumberExpression(value: Integer.parseInt(ctx.INT().toString()))
    }

    @Override
    Expression visitAddition(BasicParser.AdditionContext ctx) {
        def left = visit(ctx.expression(0))
        def right = visit(ctx.expression(1))

        new AdditionExpression(left: left, right: right)
    }

    @Override
    Expression visitSubtraction(BasicParser.SubtractionContext ctx) {
        def left = visit(ctx.expression(0))
        def right = visit(ctx.expression(1))

        new SubtractionExpression(left: left, right: right)
    }

    @Override
    Expression visitMultiplication(BasicParser.MultiplicationContext ctx) {
        def left = visit(ctx.expression(0))
        def right = visit(ctx.expression(1))

        new MultiplicationExpression(left: left, right: right)
    }

    @Override
    Expression visitDivision(BasicParser.DivisionContext ctx) {
        def left = visit(ctx.expression(0))
        def right = visit(ctx.expression(1))

        new DivisionExpression(left: left, right: right)
    }

    @Override
    Expression visitParentheses(BasicParser.ParenthesesContext ctx) {
        def parentheses = visit(ctx.expression())
        parentheses
    }
}
