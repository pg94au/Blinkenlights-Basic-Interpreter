package org.blinkenlights.basic.visitors

import org.blinkenlights.basic.equations.DoesNotEqualEquation
import org.blinkenlights.basic.equations.EqualsEquation
import org.blinkenlights.basic.equations.Equation
import org.blinkenlights.basic.equations.GreaterThanEquation
import org.blinkenlights.basic.equations.GreaterThanOrEqualEquation
import org.blinkenlights.basic.equations.LessThanEquation
import org.blinkenlights.basic.equations.LessThanOrEqualEquation
import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser

class EquationVisitor extends BasicBaseVisitor<Equation> {
    @Override
    Equation visitEquals(BasicParser.EqualsContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new EqualsEquation(left: left, right: right)

        equation
    }

    @Override
    Equation visitDoesNotEqual(BasicParser.DoesNotEqualContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new DoesNotEqualEquation(left: left, right: right)

        equation
    }

    @Override
    Equation visitGreaterThan(BasicParser.GreaterThanContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new GreaterThanEquation(left: left, right: right)

        equation
    }

    @Override
    Equation visitLessThan(BasicParser.LessThanContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new LessThanEquation(left: left, right: right)

        equation
    }

    @Override
    Equation visitGreaterThanOrEqual(BasicParser.GreaterThanOrEqualContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new GreaterThanOrEqualEquation(left: left, right: right)

        equation
    }

    @Override
    Equation visitLessThanOrEqual(BasicParser.LessThanOrEqualContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def left = expressionVisitor.visit(ctx.expression(0))
        def right = expressionVisitor.visit(ctx.expression(1))
        def equation = new LessThanOrEqualEquation(left: left, right: right)

        equation
    }
}
