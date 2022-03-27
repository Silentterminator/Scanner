import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.exit;

public class Scanner {
    // Character arrays initialized (array, token, & errorChar)
    private final File file;                                // from our text document. Seen in the Main.java
    private final List<Character> characterArray = new ArrayList<>();
    private final List<String> tokens = new ArrayList<>();
    private final Set<Character> errorChar = new HashSet<>();

    // How to find the comments
    private final char EMPTY_CHAR_CHARACTER = '\u0000';     // ASCII values
    private final char NEW_LINE_CHARACTER = '\n';           // What gets read out in as actual characters in the character array.
                                                            // So when those values are found, it knows that we are ending a comment
    // Helps find digit.digit
    private final char PERIOD = '.';

    public Scanner(File file) {                             //Called from the main with the text file used
        this.file = file;                                   //Setting the file to be used throughout the code
        addErrorCharacters();                               //Calling addErrorCharacters function
    }

    public void parse() throws IOException {
        // Reads text from file inputted
        BufferedReader br = new BufferedReader(new FileReader(file));

        int c;                                              // Incrementing value

        // Read characters in and store them in our character array
        while ((c = br.read()) != -1) {
            char ch = (char) c;                             // setting Ch<- Character to the character value of c
            characterArray.add(ch);
        }

        boolean inComment = false;                          // Variable to let us know if we're in a comment or not
        char commentCharacter = EMPTY_CHAR_CHARACTER;       // Holds what type of comment we are in, denoted by second character, '/' or '*'

        // Whole automata is within this for loop
        for (int i = 0; i < characterArray.size()-1; i++) { // Starts from 0, going to size()-1 and incrementing by 1
            char currentChar = characterArray.get(i);       // Initial State. keeps increasing as it keeps going through the for loop
            char nextChar = characterArray.get(i+1);        // Peek value

            // if we have /* or //, set inComment to true, that way we don't parse more values until end of line or end of comment
            if (currentChar == '/' && (nextChar == '*' || nextChar == '/')) {
                inComment = true;
                commentCharacter = nextChar;                // Set to the peak
            }

            if (inComment) {                                //The above is If it's inside a comment
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
                    // If no comments then it checks for digits 0-9. So it's a digit or a period followed my a digit
                    if (Character.isDigit(currentChar) || (currentChar == PERIOD && Character.isDigit(nextChar))) {
                        while (Character.isDigit(nextChar) || nextChar == PERIOD) {
                            i++;                            // If true then it continues looking through the array
                            nextChar = characterArray.get(i+1); // Moves the nextChar to find the ending pert of the number that we are looking for
                        }                                       // because the longest value is the token
                        tokens.add("number");
                    }
                    //Find read and write within the characterArray
                    if (currentChar == 'r' && nextChar == 'e' && characterArray.get(i+2) == 'a' && characterArray.get(i+3) == 'd') {
                        tokens.add("read");
                        i+=3;
                    } else if (currentChar == 'w' && nextChar == 'r' && characterArray.get(i+2) == 'i' && characterArray.get(i+3) == 't' && characterArray.get(i+4) == 'e') {
                    tokens.add("write");
                    i+=4;

                    //Finding symbols within the characterArray
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

                    // Assigning a value to something is also a token :=
                    } else if (currentChar == ':' && nextChar == '=') {
                        tokens.add("assign");

                    //With in this ID token we are looking if currentChar & nextChar is a letter
                    } else if (Character.isAlphabetic(currentChar) && Character.isAlphabetic(nextChar)) {

                    // Checks if the next character is a letter or a digit while the first one is a letter
                        while(Character.isAlphabetic(nextChar) && Character.isAlphabetic(nextChar+1) || Character.isDigit(nextChar+1)) {
                            i++;
                            nextChar = characterArray.get(i+1); //Goes until the very end unit no more digits or letters
                        }
                        tokens.add("id");                       //Then adds id token
                    }
                }
        }
        System.out.println(tokens);                             //Print all tokens found
    }

    private void addErrorCharacters() {
        errorChar.add(','); errorChar.add('!'); errorChar.add('@'); errorChar.add('#'); errorChar.add('$'); errorChar.add('%');
        errorChar.add('^'); errorChar.add('&'); errorChar.add('_'); errorChar.add('|'); errorChar.add('?'); errorChar.add('[');
        errorChar.add(']'); errorChar.add('{'); errorChar.add('}'); errorChar.add('<'); errorChar.add('>'); errorChar.add('"');
        errorChar.add('`'); errorChar.add('~');
    }
}
