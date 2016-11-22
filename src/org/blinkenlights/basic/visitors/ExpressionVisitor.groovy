package org.blinkenlights.basic.visitors

import org.blinkenlights.basic.expressions.AdditionExpression
import org.blinkenlights.basic.expressions.DivisionExpression
import org.blinkenlights.basic.expressions.Expression
import org.blinkenlights.basic.expressions.MultiplicationExpression
import org.blinkenlights.basic.expressions.NumberExpression
import org.blinkenlights.basic.expressions.SubtractionExpression
import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser

class ExpressionVisitor extends BasicBaseVisitor<Expression> {
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
