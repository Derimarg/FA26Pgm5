
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Functions {

    public static boolean isDigits(String inString) {
        if (inString == null || inString.isEmpty())
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    /*PRE: Receives a PrintWriter 'out
     *POST: Pushes each word onto the stack, then pops off
     *      each word comparing it letter by letter to itself.
     *      Prints whether or not each word is a plaindrome to the
     *      screen and output file
    */
    public static void testPalindrome(PrintWriter out) {
        System.out.println("\nSTACK 1: PALINDROME");
        out.println("\nSTACK 1: PALINDROME");

        MyStack<Character> stack = new MyStack<>();

        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("tacocat",
                "bubba", "a", "mom", "abba", "abccba", "abcba"));

        String msg = "";
        System.out.println(msg);
        out.println(msg);
        out.println();

        boolean palindrome = false;

        //Loop through wordList
        for (String word: wordList){
            //Put each word onto the stack
            for (int i = 0; i < word.length(); i++){
                stack.push((Character)word.charAt(i));
            }
            //Pop letter off the stack factualizing if the word
            //is a plaindrome or not.
            for (int i = 0; i < word.length(); i++){
                //If any letters mismatch, the word is not a palindrome
                if (!((Character)word.charAt(i)).equals(stack.pop())){
                    //Print the result to the screena and output file
                    msg = String.format("%-8s is a palindrome: false",word);
                    System.out.println(msg);
                    out.println(msg);
                    break;
                }
                //If all character have matched, the word is a palindrome
                //assign palindrome = true
                if (i == word.length() - 1){
                    palindrome = true;
                }
            }
            if (palindrome){
                //If the word is a palindrome, print the result to 
                //the screen and output file
                msg = String.format("%-8s is a palindrome: true",word);
                System.out.println(msg);
                out.println(msg);
                palindrome = false;
            }
        }

    }

    /*PRE: Receives a PrintWriter 'out'.
     *POST: Prints the mathematical result of the expression
     *      or an invalid message to the screen and output file
     */
    public static void testPostFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 2: POSTFIX EVALUATOR");
        out.println("\nSTACK 2: POSTFIX EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "5 7 * 15 + 25 *",
                "5 0 / 4 +",
                "15 2 * 3 / 15 +",
                "3 5 7 + *",
                "3 5 6"));

        for (String line: expList){
            String result = evaluatePostfix(line);
            System.out.println(result);
            out.println(result);
        }
        out.println();
    }


    /*PRE: Receives a String 'expression'
     *POST: Returns the mathematical result of the expression
     *      or an invalid message
    */
    public static String evaluatePostfix(String expression){
        MyStack<Integer> stack = new MyStack<Integer>();
        String[] tokens = expression.split(" ");
        int left,right;
        
        for (String token: tokens){
            if (token.matches("-?\\d+")){
                stack.push((Integer)Integer.parseInt(token));
            }
            else {
                try{
                    if (stack.size() < 2) {
                    throw new IllegalStateException("Operators require two numbers");
                    }
                    right = stack.pop();
                    left = stack.pop();
                    switch(token){
                        case "/":
                            stack.push(left / right);
                            break;
                        case "*":
                            stack.push(left * right);
                            break;
                        case "+":
                            stack.push(left + right);
                            break;
                        case "-":
                            stack.push(left - right);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + token);
                    }
                }
                catch (Exception e){
                    return String.format("The result of the postfix expression: %s is invalid",expression);
                }
            }
        }
        if (stack.size() != 1) {
            return String.format("The result of the postfix expression: %s is invalid",expression);
        }
        return String.format("The result of the postfix expression: %s = %d",expression,stack.pop());
    }


    /**
     * PRE: PrintWriter out is initialized and ready to write.
     * POST: Evaluates each prefix expression and prints
     *       the results or error messages to the console
     *       and output file
     * 
     */
    public static void testPreFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 3: PREFIX EVALUATOR");
        out.println("\nSTACK 3: PREFIX EVALUATOR");

        // Initialize prefix expressions to test
        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "+ * 7 5 + 6 7",
                "- + 7 * 4 5 + 2 0",
                "- / 18 / 6 3 2",
                "* 25 + 15 * 7 5",
                "+ 4 / 0 5",
                "+ 15 / 3 * 2 15",
                "* + 7 5 3",
                "6 5 3"));

        // Evaluate each prefix expression
        for (String exp : expList) {
            String output;

            try {
                int result = evaluatePrefix(exp);

                //Formate the result for valid expressions
                output = "The result of the prefix expression: " + exp + " = " + result;

            } catch (IllegalArgumentException | ArithmeticException e) {

                //Handles invalid expressions
                output = "The expression: " + exp + " is invalid.";
            }

            // Print final results to console and output file
            System.out.println(output);
            out.println(output);
        }

        out.println();
    }

    /**
     * PRE: expression is a non-null prefix expression
     * POST: Returns the evaluated result of the valid expression
     *       Invalid expressions must be handled.
     * 
     */
    public static int evaluatePrefix(String expression) {
        MyStack<Integer> stack = new MyStack<>();

        String[] tokens = expression.trim().split("\\s+");

        //Traverse the tokens from right to left
        for (int i = tokens.length - 1; i >= 0; i--) {
            
            String token = tokens[i];

            // Check if the token is a number
            // Also checks for negitive numbers
            if (token.matches("-?\\d+")) {
                stack.push(Integer.valueOf(token));
            }
            else{
                // Ensure there are at least two operands available
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid prefix expression");
                }

                // Pop the two operands
                int left = stack.pop();
                int right = stack.pop();

                // Perform the arithmetic operation
                int result;

                switch(token) {
                    case "+":
                        result = left + right;
                        break;
                    case "-":
                        result = left - right;
                        break;
                    case "*":
                        result = left * right;
                        break;
                    case "/":
                        if (right == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = left / right;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }

                stack.push(result);
            }
        }

        // A valid prefix expression must leave exactly one value
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid prefix expression");
        }

        // return final evaluated result
        return stack.pop();
    }


    /**
     * PRE: Accepts a valid PrintWriter object for file output
     * POST: Converts a list of decimal numbers to their binary representation using
     * a stack and writing the ouputs to the report file
     */
    public static void testDecToBinary(PrintWriter out) {
        System.out.println("\nSTACK 4: DECIMAL TO BINARY EVALUATOR");
        out.println("\nSTACK 4: DECIMAL TO BINARY EVALUATOR");

        ArrayList<Integer> binary = new ArrayList<>(Arrays.asList(15, 81, 105, 255, 256, 0, 1));

        // Iterate throu the binary list
        for (int num : binary) {
            MyStack<Integer> stack = new MyStack<>();

            // Base case for 0
            if (num == 0) {
                stack.push(0);
            } else {
                // Get the remainder as the binary bit and push it to the stack
                while (num > 0) {
                    stack.push(num % 2);
                    num /= 2; // Reduce the number until it reaches 0
                }
            }

            StringBuilder binaryStr = new StringBuilder();
            // remove the elements of the stack and append it to the string until stack is
            // empty
            while (!stack.isEmpty()) {
                binaryStr.append(stack.pop());
            }

            String result = "The decimal value " + num + " is " + binaryStr.toString() + " in binary";
            System.out.println(result);
            out.println(result);
        }
        out.println();
    }

    /**
     * PRE: PrintWriter out is initialized and ready to write.
     * POST: Tests each expression for tha balanced brackets and
     *       prints the results to the console and output file
     * 
     */

    public static void testIsBalanced(PrintWriter out) {
        System.out.println("\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");
        out.println("\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");
        
        // Initialize expressions to test for balanced brackets
        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "[7 + (4 - 3)] - (3 * 4)",
                "[7 + (4 - 3] - (3 * 4)",
                "4 + 6 - 7",
                "([x + y] + {z * w})",
                "{(1 + [2 * 3]) - 4}",
                "([x + y] + z}"));
        
        // Evaluate each expression using the stack based algo
        for (String exp : expList) {
            boolean result = isBalanced(exp);

            //Format for the print results to console and output file
            String output = "The expression " + exp + (result ? " is balanced" : " is not balanced"); 

            System.out.println(output);
            out.println(output);
        }

        out.println();
    }

    /*
     * PRE: The expression is a non-null String
     * POST: Returns true if all brackets are correctly
     *       matched, false otherwise
     */
    public static boolean isBalanced(String expression) {
        MyStack<Character> stack = new MyStack<>();

        // Traverse each character in the expression
        for(int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            // Push opening brackets onto the stack, pop and check for matching closing brackets
            if(current == '(' || current == '[' || current == '{') {
                stack.push(current);
            }
            else if(current == ')' || current == ']' || current == '}') {

                // A closing bracket without an opening bracket is invalid
                if (stack.isEmpty()) {
                    return false; 
                }
                
                // Remove the most recent opening bracket
                char top = stack.pop();

                // Check if the opening and closing brackets match
                if ((current == ')' && top != '(') ||
                    (current == ']' && top != '[') ||
                    (current == '}' && top != '{')) {
                    return false;
                }

            }
        }
        // Any remaining opening brackets mean the expression is unbalanced
        return stack.isEmpty();
    }




    /*
     * PRE: Accepts a valid PrintWriter object for file output
     * POST: Does a set of operations base on a list of commands utilizing undo/redo
     * stacks
     */
    public static void testUndoRedo(PrintWriter out) {
        System.out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");
        out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "D add a", "D add b", "D add c", "R", "U", "U", "R", "U", "D add d", "U", "U", "U", "R", "F", "Q"));

        MyStack<String> undoStack = new MyStack<>();
        MyStack<String> redoStack = new MyStack<>();

        for (String line : expList) {

            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            // Split into [command, element]
            String[] parts = trimmed.split("\\s+", 2);
            String command = parts[0].toUpperCase();

            switch (command) {
                case "D" -> { // Add element
                    String item = (parts.length > 1) ? parts[1] : "";
                    undoStack.push(item);
                    // Clear history on new action
                    redoStack.clear();

                    String msg = "performing: " + item;
                    System.out.println(msg);
                    out.println(msg);
                }
                case "R" -> { // Redo action
                    if (redoStack.isEmpty()) {
                        String msg = "redo: stack is empty";
                        System.out.println(msg);
                        out.println(msg);

                    } else {
                        String redoneItem = redoStack.pop();
                        undoStack.push(redoneItem); // Move back to active stack

                        String msg = "redoing: command: " + redoneItem;
                        System.out.println(msg);
                        out.println(msg);
                    }
                }
                case "U" -> { // Undo action
                    if (undoStack.isEmpty()) {
                        String msg = "undo: stack is empty";
                        System.out.println(msg);
                        out.println(msg);
                    } else {
                        String undoneItem = undoStack.pop();
                        redoStack.push(undoneItem); // Move to redo stack

                        String msg = "undoing: command: " + undoneItem;
                        System.out.println(msg);
                        out.println(msg);
                    }
                }
                case "Q" -> { // Terminate actions
                    String msg = "program ending";
                    System.out.println(msg);
                    out.println(msg);
                    return;
                }
                default -> {
                    String msg = "invalid command entered: " + command;
                    System.out.println(msg);
                    out.println(msg);
                }

            }
        }

        out.println();
    }

}
