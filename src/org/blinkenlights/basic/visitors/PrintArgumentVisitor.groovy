package org.blinkenlights.basic.visitors

import org.blinkenlights.basic.gen.BasicBaseVisitor
import org.blinkenlights.basic.gen.BasicParser
import org.blinkenlights.basic.statements.PrintArgument

class PrintArgumentVisitor extends BasicBaseVisitor<String> {
    def Arguments = []
    //TODO: Rename the grammar label so that these are visitQuotedStringArgument, etc?

    @Override
    String visitPrintQuotedString(BasicParser.PrintQuotedStringContext ctx) {
        def quotedString = ctx.QUOTED_STRING().text
        quotedString = quotedString[1..-2]
        def printArgument = new PrintArgument(quotedString)

        Arguments.add(printArgument)

        printArgument
    }

    @Override
    String visitPrintExpression(BasicParser.PrintExpressionContext ctx) {
        def expressionVisitor = new ExpressionVisitor()
        def expression = expressionVisitor.visit(ctx.expression())
        def printArgument = new PrintArgument(expression)

        Arguments.add(printArgument)

        printArgument
    }

    @Override
    String visitArgSeparator(BasicParser.ArgSeparatorContext ctx) {
        def separator
        switch (ctx.text) {
            case ',':
                separator = ' '
                break
            case ';':
                separator = ''
                break;
        }
        def printArgument = new PrintArgument(separator)

        Arguments.add(printArgument)

        return super.visitArgSeparator(ctx)
    }
}
