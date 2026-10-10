
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

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

    public static void testPalindrome(PrintWriter out) {
        System.out.println("\nSTACK 1: PALINDROME");
        out.println("\nSTACK 1: PALINDROME");

        MyStack<Character> stack = new MyStack<>();

        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("tacocat",
                "bubba", "a", "mom", "abba", "abccba", "abcba"));

        // REMOVE: testing stack
        stack.push('t');
        stack.push('a');
        stack.push('c');
        stack.push('o');
        stack.push('c');
        stack.push('a');
        stack.push('t');
        System.out.println(stack.stackString());
        out.println(stack.stackString());
        out.println("\n");
    }

    public static void testPostFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 2: POSTFIX EVALUATOR");
        out.println("\nSTACK 2: POSTFIX EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "5 7 * 15 + 25 *",
                "5 0 / 4 +",
                "15 2 * 3 / 15 +",
                "3 5 7 + *",
                "3 5 6"));

        out.println("\n");
    }

    public static void testPreFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 3: PREFIX EVALUATOR");
        out.println("\nSTACK 3: PREFIX EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "+ * 7 5 + 6 7",
                "- + 7 * 4 5 + 2 0",
                "- / 18 / 6 3 2",
                "* 25 + 15 * 7 5",
                "+ 4 / 0 5",
                "+ 15 / 3 * 2 15",
                "* + 7 5 3",
                "6 5 3"));

        out.println("\n");
    }

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
        out.println("\n");
    }

    public static void testIsBalanced(PrintWriter out) {
        System.out.println("\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");
        out.println("\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "[7 + (4 - 3)] - (3 * 4)",
                "[7 + (4 - 3] - (3 * 4)",
                "4 + 6 - 7",
                "([x + y] + {z * w})",
                "{(1 + [2 * 3]) - 4}",
                "([x + y] + z}"));

        out.println("\n");
    }

    public static void testUndoRedo(PrintWriter out) {
        System.out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");
        out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
                "D add a", "D add b", "D add c", "R", "U", "U", "R", "U", "D add d", "U", "U", "U", "R", "F", "Q"));

        out.println("\n");
    }

}
