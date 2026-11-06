import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Problem 2: The Parenthesis with the Wild Card.
 */
public class ParenthesisValidator {

    public static boolean isValid(String s) {
        Stack stack = new Stack();
        Stack helper = new Stack(); 

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '$') {
                break; // end of string

            } else if (c == '(' || c == '*') {
                stack.push(c);

            } else if (c == ')') {
                boolean matched = false;

                
                while (!stack.isEmpty()) {
                    char t = stack.pop();
                    if (t == '(') {
                        matched = true;
                        break;
                    }
                    helper.push(t);
                }

                if (!matched) {
                    
                    if (helper.isEmpty()) {
                        return false; // nothing to match the ')'
                    }
                    helper.pop();
                }

                // put the untouched elements back in their original order
                while (!helper.isEmpty()) {
                    stack.push(helper.pop());
                }
            }
            // no other characters expected
        }

        // remaining '(' have to be paired with a later '*'
        int stars = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop(); 
            if (c == '*') {
                stars++;
            } else { // '('
                if (stars == 0) {
                    return false;
                }
                stars--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String fileName = (args.length > 0) ? args[0] : "testcases.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }
                System.out.println(line + " - " + (isValid(line) ? "Valid" : "Invalid"));
            }
        } catch (IOException e) {
            System.out.println("Could not read file '" + fileName + "': " + e.getMessage());
        }
    }
}