package org.blinkenlights.basic;

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
19 LET FOO2 = 1024
20 LET NEGATIVE = -512
21 LET POSITIVE = NEGATIVE + 1024
50 GOSUB 1000
60 PRINT "Returned from Subroutine"
70 PRINT 123 + 234
80 PRINT "Hello", 123
100 GOTO 2000
1000 PRINT "In Subroutine"
1010 RETURN
2000 PRINT "At line 2000."
2010 PRINT "Space", "between"
2020 PRINT "Nothing"; "between"
2030 PRINT "Answer="; 42
2040 PRINT "G =", G
2050 PRINT FOO2
2060 PRINT "NEGATIVE =", NEGATIVE
2070 PRINT "POSITIVE =", POSITIVE
3000 END
3010 PRINT "Shouldn't get here"
"""

        def interpreter = new Interpreter(program)
        interpreter.executeProgram();
    }
}
