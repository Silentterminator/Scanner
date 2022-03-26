import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.exit;

public class Scanner {

    private final File file;
    private final List<Character> characterArray = new ArrayList<>();
    private final List<String> tokens = new ArrayList<>();
    private final Set<Character> errorChar = new HashSet<>();

    // How to find the comments
    private final char EMPTY_CHAR_CHARACTER = '\u0000';
    private final char NEW_LINE_CHARACTER = '\n';
    // Helps find digit.digit
    private final char PERIOD = '.';

    public Scanner(File file) {
        this.file = file;
        addErrorCharacters();
    }

    public void parse() throws IOException {
        // Reads text from file
        BufferedReader br = new BufferedReader(new FileReader(file));

        int c;
        // Read characters in and store them in our character array
        while ((c = br.read()) != -1) {
            char ch = (char) c;
            characterArray.add(ch);
        }

        boolean inComment = false;                          // Variable to let us know if we're in a comment or not
        char commentCharacter = EMPTY_CHAR_CHARACTER;       // Holds what type of comment we are in, denoted by second character, '/' or '*'

        for (int i = 0; i < characterArray.size()-1; i++) {
            char currentChar = characterArray.get(i);
            char nextChar = characterArray.get(i+1);        // Peek value

            // if we have /* or //, set inComment to true, that way we don't parse more values until end of line or end of comment
            if (currentChar == '/' && (nextChar == '*' || nextChar == '/')) {
                inComment = true;
                commentCharacter = nextChar;
            }

            if (inComment) {
                // check for closing comment - we're in a // comment if the first condition is met
                if (commentCharacter == '/' && currentChar == NEW_LINE_CHARACTER) {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                }
                // Check for */ to close comment
                else if (currentChar == '*' && nextChar == '/') {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                    i++;
                }
            } else {
                    // Finding error characters within characterArray
                    if (errorChar.contains(currentChar)) {
                        System.out.println("Error");
                        exit(0);                        //Exiting program (found error character)
                    }

                    if (Character.isDigit(currentChar) || (currentChar == PERIOD && Character.isDigit(nextChar))) {
                        while (Character.isDigit(nextChar) || nextChar == PERIOD) {
                            i++;
                            nextChar = characterArray.get(i+1);
                        }
                        tokens.add("number");
                    }

                    if (currentChar == 'r' && nextChar == 'e' && characterArray.get(i+2) == 'a' && characterArray.get(i+3) == 'd') {
                        tokens.add("read");
                        i+=3;
                    } else if (currentChar == 'w' && nextChar == 'r' && characterArray.get(i+2) == 'i' && characterArray.get(i+3) == 't' && characterArray.get(i+4) == 'e') {
                    tokens.add("write");
                    i+=4;
                    } else if (currentChar == '(') {
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
                    } else if (currentChar == ':' && nextChar == '=') {
                        tokens.add("assign");
                    } else if (Character.isAlphabetic(currentChar) && Character.isAlphabetic(nextChar)) {
                        while(Character.isAlphabetic(nextChar) && Character.isAlphabetic(nextChar+1) || Character.isDigit(nextChar+1)) {
                            i++;
                            nextChar = characterArray.get(i+1);
                        }
                        tokens.add("id");
                    }
                }
        }
        System.out.println(tokens);
    }

    private void addErrorCharacters() {
        errorChar.add(','); errorChar.add('!'); errorChar.add('@'); errorChar.add('#'); errorChar.add('$'); errorChar.add('%');
        errorChar.add('^'); errorChar.add('&'); errorChar.add('_'); errorChar.add('|'); errorChar.add('?'); errorChar.add('[');
        errorChar.add(']'); errorChar.add('{'); errorChar.add('}'); errorChar.add('<'); errorChar.add('>'); errorChar.add('"');
        errorChar.add('`'); errorChar.add('~');
    }
}
