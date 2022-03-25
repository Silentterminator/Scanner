import java.io.*;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class ScannerOld {

    private File file;
    private int state;
    private final List<String> tokens;

    private final Set<Character> possibleCharacters = new HashSet<>();

    public ScannerOld(File file) throws IOException {
        this.file = file;
        tokens = new ArrayList<>();
        parseFile();
        addTokens();
    }

    public void parseFile() throws IOException {
        // while not EOF run automata
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        Character[] numbers = new Character[]{'0','1','2','3','4','5','6','7','8','9'};
        String line = br.readLine();
        ArrayList<String> st = new ArrayList<>();

        // Condition holds true till
        // there is character in a string

        while (line != null)
        {
            // Print the string
            st.add(line);
            line = br.readLine();

        }
        for(int i = 0; i<st.size();i++)
        {
            if(st.get(i).contains("read")||st.get(i).contains("Read"))
                tokens.add("read");
            if(st.get(i).contains("write")||st.get(i).contains("Write"))
                tokens.add("write");
            if(!(st.get(i).contains("/*")&&st.get(i).contains("*/")||st.get(i).contains("//")))
            {
                if(st.get(i).contains("*"))
                {
                    tokens.add("times");
                }
                if((st.get(i).contains(" ")))
                {
//                    if(!st.get(i).contains(numbers[i]))
                        System.out.println("ID: "+st.get(i));
                }
            }

            for (Character number : numbers) {
//                if (st.get(i).contains(number))
                    tokens.add("number");
            }

        }
        System.out.println(tokens);
        br.close();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private void addTokens() {
        List<Character> token = new ArrayList<>(Arrays.asList('(', ')', '+', '-', '*', ':', '=', '.',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));

//        List<Character> token = new ArrayList<>();
        // add lower case alphabet to tokens
        for (int i = 65; i <= 90; i++) {
            token.add((char) i);
        }

        // add upper case alphabet to tokens
        for (int i = 97; i <= 122; i++) {
            token.add((char) i);
        }

        possibleCharacters.addAll(token);
        token.clear();
    }

}
