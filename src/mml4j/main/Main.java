package mml4j.main;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;
import mml4j.main.exceptions.ParsingException;
import mml4j.main.exceptions.TypingException;
import mml4j.main.parser.Parser;
import mml4j.main.typist.Typist;
import mml4j.main.typist.types.abstracts.Type;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // ----- Attributes -----


    /** If the REPL is running */
    private static boolean running = true;


    // ----- Class methods -----


    /**
     * Display the help message to the user
     */
    private static void displayHelp() {
        System.out.println("This is the REPL of MML4J so there are some specific commands :");
        System.out.println("   help : Display this help message");
        System.out.println("   debug : Toggle the debug mode");
        System.out.println("   exit : Exit the REPL");
        System.out.println("   quit : Same as exit");
        System.out.println("If you want to know more about MML syntax, read the MANUAL.md");
    }

    /**
     * Evaluate the input in the special commands
     *
     * @param input The input
     * @return True if the input is a special command
     */
    private static boolean replSpecial(String input) {
        switch (input) {

            // If the user want some help
            case "help":
                displayHelp();
                break;

            case "debug":
                Utils.DEBUG = !Utils.DEBUG;
                System.out.println("Debug mode is now " + (Utils.DEBUG ? "on" : "off"));
                break;

            // If the user want to exit
            case "quit":
            case "exit":
                System.out.println("Goodbye !");
                running = false;
                break;

            default:
                return false;
        }
        return true;
    }

    /**
     * Start the REPL
     */
    private static void startRepl() {
        // Prepare the user input and scanner
        String input = null;
        Scanner scanner = new Scanner(System.in);

        // The REPL loop
        while (running) {
            // Read the user input
            System.out.print("> ");
            input = scanner.nextLine();

            // Prepare the parser and the evaluator
            Parser parser = new Parser();
            Evaluator evaluator = new Evaluator();

            // Test the special inputs (quit, help...)
            if(!replSpecial(input)) {
                try {
                    // Parse the expression
                    ASTExpr expr = (ASTExpr) parser.parseString(input);

                    // Type the expression
                    Type type = Typist.typeExpression(expr);

                    // Evaluate the expression
                    Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

                    // Print the result
                    System.out.println(type + " :: " + result);
                } catch (ParsingException e) {
                    System.out.println("Cannot parse the input : " + e.getMessage());
                } catch (TypingException e) {
                    System.out.println("Error in expression typing : " + e.getMessage());
                } catch (EvaluationException e) {
                    System.out.println("Error in expression evaluation : " + e.getMessage());
                }
            }
        }
    }


    // ----- Entry point -----


    public static void main(String[] args) {
        // Print the header
        System.out.println("=============================================");
        System.out.println("  mini-ML4J 0.6 | REPL\n");
        System.out.println("  License : MIT");
        System.out.println("  Author : Hugo GUERRIER\n");
        System.out.println("  Type \"help\" for a little help message");
        System.out.println("=============================================");

        // Start the REPL
        startRepl();
    }

}