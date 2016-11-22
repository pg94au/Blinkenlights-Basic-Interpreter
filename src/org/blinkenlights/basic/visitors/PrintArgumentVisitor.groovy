package org.blinkenlights.basic.visitors

import org.blinkenlights.basic.ExpressionVisitor
import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser
import org.blinkenlights.basic.statements.PrintArgument

class PrintArgumentVisitor extends BasicBaseVisitor<PrintArgument> {
    //TODO: Rename the grammar label so that these are visitQuotedStringArgument, etc?

    @Override
    PrintArgument visitPrintQuotedString(BasicParser.PrintQuotedStringContext ctx) {
        def quotedString = ctx.QUOTED_STRING().text
        quotedString = quotedString[1..-2]
        def printArgument = new PrintArgument(quotedString)

        printArgument
    }

    @Override
    PrintArgument visitPrintExpression(BasicParser.PrintExpressionContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def expression = expressionVisitor.visit(ctx.expression())
        def printArgument = new PrintArgument(expression)

        printArgument
    }
}
