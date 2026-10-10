
/*
* TEAM NAMES: Derimar Gray, Ethan Lopez, Rusell Adams
* Date: 10/09/2026
* ASSIGNMENT: Program 5 - Stacks
* DESCRIPTION: This program demonstrates the Stacks Data Structure by implementing it's core methods and applying them to test different applications:
* Palindrone, Postfix Evaluator, Prefix Evaluator, Balanced Parenthesis, Decimal to Binary, and Undo/Redo Command Processor.
*/
import java.io.PrintWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            PrintWriter out = new PrintWriter("report.txt");
            Functions.testPalindrome(out);
            Functions.testPostFixExpression(out);
            Functions.testPreFixExpression(out);
            Functions.testDecToBinary(out);
            // Functions.testIsBalanced(out);
            Functions.testUndoRedo(out);
            out.close();
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }
}
