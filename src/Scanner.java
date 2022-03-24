import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Scanner {

    private File file;
    private int state;
    private final List tokens;
    //public int numbers{0,1,2,3,4,5,6,7,8,9};

    public Scanner(File file) throws IOException {
        this.file = file;
        tokens = new ArrayList<String>();
        parseFile();
    }

    public void parseFile() throws IOException {
        // while not EOF run automata
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String line = br.readLine();
        ArrayList<String> st = new ArrayList<String>();

        // Condition holds true till
        // there is character in a string
        while (line != null)
        {
            // Print the string
                //System.out.println(st);
            st.add(line);
            line = br.readLine();
        }

        for(int x = 0; x<st.size();x++)
        {
            if(st.get(x).contains("read")||st.get(x).contains("Read"))
                tokens.add("read");
            if(st.get(x).contains("write")||st.get(x).contains("Write"))
                tokens.add("write");
        }
        System.out.println(tokens);

        br.close();
    }

    public void automata() {
        switch (this.state) {
            case 1:

                break;
        }
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

}
