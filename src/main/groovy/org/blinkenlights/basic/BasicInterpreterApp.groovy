package org.blinkenlights.basic;

public class BasicInterpreterApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            println("Usage:  BasicInterpreterApp <programFile>")
            return
        }

        def program = new File(args[0]).text

        def interpreter = new Interpreter(program)
        interpreter.executeProgram();
    }
}
