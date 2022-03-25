import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private final File file;
    private final List<Character> characterArray = new ArrayList<>();
    private final char EMPTY_CHAR_CHARACTER = '\u0000';
    private final char NEW_LINE_CHARACTER = '\n';

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
        for (int i = 0; i < characterArray.size(); i++) {
            // if we have /* or //, set inComment to true, that way we don't parse more values until end of line or end of comment
            if (characterArray.get(i) == '/' && (characterArray.get(i+1) == '*' || characterArray.get(i+1) == '/')) {
                inComment = true;
                commentCharacter = characterArray.get(i+1);
            }
            if (inComment) {
                // check for closing comment - we're in a // comment if the first condition is met
                if (commentCharacter == '/' && characterArray.get(i) == NEW_LINE_CHARACTER) {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                } else if (characterArray.get(i) == '*' && characterArray.get(i+1) == '/') {
                    inComment = false;
                    commentCharacter = EMPTY_CHAR_CHARACTER;
                    i++;
                }
            } else {
                // TODO OTHER CHARACTER CHECK HERE

            }
        }
    }
}
