import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private final File file;
    private final List<Character> characterArray = new ArrayList<>();
    private final List<String> tokens = new ArrayList<>();

    private final char EMPTY_CHAR_CHARACTER = '\u0000';
    private final char NEW_LINE_CHARACTER = '\n';

    private final char PERIOD = '.';

    public Test(File file) {
        this.file = file;
    }

    public void parse() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        int c;
        // Read characters in and store them in our character array
        while ((c = br.read()) != -1) {
            char ch = (char) c;
            characterArray.add(ch);
        }

        boolean inComment = false;                          // Variable to let us know if we're in a comment or not
        char commentCharacter = EMPTY_CHAR_CHARACTER;       // Reference to if we're in a // comment or /* comment
        for (int i = 0; i < characterArray.size()-1; i++) {

            char currentChar = characterArray.get(i);
            char nextChar = characterArray.get(i+1);

            // if we have /* or //, set inComment to true, that way we don't parse more values until end of line or end of comment
            if (currentChar == '/' && (nextChar == '*' || nextChar == '/')) {
                inComment = true;
                commentCharacter = characterArray.get(i+1);
            }
            if (inComment) {
                // check for closing comment - we're in a // comment if the first condition is met
                if (commentCharacter == '/' && currentChar == NEW_LINE_CHARACTER) {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                } else if (currentChar == '*' && nextChar == '/') {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                    i++;
                }
            } else {
                if (Character.isDigit(currentChar) || (currentChar == PERIOD && Character.isDigit(nextChar))) {
                    while (Character.isDigit(nextChar) || nextChar == PERIOD) {
                        i++;
                        nextChar = characterArray.get(i+1);
                    }
                    tokens.add("number");
                }

                if (currentChar == '(') {
                    tokens.add("lparen");
                } else if (currentChar == '*') {
                    tokens.add("times");
                } else if (currentChar == '/') {
                    tokens.add("div");
                } else if (currentChar == '+') {
                    tokens.add("plus");
                } else if (currentChar == '-') {
                    tokens.add("minus");
                } else if (currentChar == ')') {
                    tokens.add("rparen");
                }

                // symbols
                // search for (
                // Search for +
                // search for -
                // search for *
                // search for /
                // search for )
                // do the fucking math (parse tree?)
                // add to tokens

                // 5+1 ----> number plus number
                // assign a variable??? := WALRUS OPERATOR print("assign")
                // search for ., if followed by digit^, print("number")
                // regular digit 99999999, print("number") BUT after finds digits, look for period, signifies a decimal, PRINT("NUMBER")
                // letter,digit = token - letter = token
            }
        }
        System.out.println(tokens);
    }
}
