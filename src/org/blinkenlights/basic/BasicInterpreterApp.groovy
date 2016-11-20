package org.blinkenlights.basic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.blinkenlights.basic.gen.BasicLexer;
import org.blinkenlights.basic.gen.BasicParser;
import org.blinkenlights.basic.statements.Statement;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NavigableMap;

public class BasicInterpreterApp {
    public static void main(String[] args) throws Exception {
//        String inputFile = null;
//        if ( args.length>0 ) inputFile = args[0];
//        InputStream is = System.in;
//        if ( inputFile!=null ) is = new FileInputStream(inputFile);

        def program = """
1 FOR X=1 TO 3
2 PRINT "X Looping"
3 FOR Y=1 TO 2
4 PRINT "Y Looping"
5 NEXT Y
9 NEXT X
10 PRINT "Call Subroutine"
11 LET A = 100
12 LET B = 12 + 23
13 LET C = 12 + 23 - 34
14 LET D = 3 * 6
15 LET E = 8 / 2
16 LET F = 2 + (3 * 3)
17 LET G = 3 * ((8 / 2 + 4) * 5 - 2)
20 GOSUB 1000
30 PRINT "Returned from Subroutine"
35 GOTO 2000
1000 PRINT "In Subroutine"
1010 RETURN
2000 PRINT "At line 2000."
2010 END
2020 PRINT "Shouldn't get here"
"""

        def programStream = new ByteArrayInputStream(program.getBytes());
//        StringBuilder sbProgram = new StringBuilder();
//        sbProgram.append("1 FOR X=1 TO 3\n");
//        sbProgram.append("2 PRINT \"X Looping\"\n");
//        sbProgram.append("3 FOR Y=1 TO 2\n");
//        sbProgram.append("4 PRINT \"Y Looping\"\n");
//        sbProgram.append("5 NEXT Y\n");
//        sbProgram.append("9 NEXT X\n");
//
//        sbProgram.append("10 PRINT \"Call Subroutine\"\n");
//        sbProgram.append("11 LET A = 100\n");
//        sbProgram.append("12 LET B = 12 + 23\n");
//        sbProgram.append("13 LET C = 12 + 23 - 34\n");
//        sbProgram.append("14 LET D = 3 * 6\n");
//        sbProgram.append("15 LET E = 8 / 2\n");
//        sbProgram.append("16 LET F = 2 + (3 * 3)\n");
//        sbProgram.append("17 LET G = 3 * ((8 / 2 + 4) * 5 - 2)\n"); // 114
//
//        sbProgram.append("20 GOSUB 1000\n");
//        sbProgram.append("30 PRINT \"Returned from Subroutine\"\n");
//        sbProgram.append("35 GOTO 2000\n");
//        sbProgram.append("1000 PRINT \"In Subroutine\"\n");
//        sbProgram.append("1010 RETURN\n");
//        sbProgram.append("2000 PRINT \"At line 2000.\"\n");
//        sbProgram.append("2010 END\n");
//        sbProgram.append("2020 PRINT \"Shouldn't get here\"\n");
//        InputStream programStream = new ByteArrayInputStream(sbProgram.toString().getBytes());

        ANTLRInputStream input = new ANTLRInputStream(programStream);
        BasicLexer lexer = new BasicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BasicParser parser = new BasicParser(tokens);
        ParseTree tree = parser.program();

        TreeVisitor visitor = new TreeVisitor();
        visitor.visit(tree);

        System.out.println("Executing...");
        System.out.println("-------------------------------");

        NavigableMap<Integer, Statement> statements = visitor.getStatements();

//        Integer currentLineNumber = statements.firstEntry().getKey();
//
//        Statement currentStatement = statements.get(currentLineNumber);
//
//        currentStatement.execute();

        Interpreter interpreter = new Interpreter();
        interpreter.executeProgram(statements);

//        for (Statement statement : org.blinkenlights.basic.statements.values()) {
//            statement.execute();
//        }

        //System.out.println(tree.toStringTree(parser));
    }
}
