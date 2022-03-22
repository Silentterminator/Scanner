import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Scanner {

    private File file;
    private int state;
    private final List tokens;

    public Scanner(File file) throws IOException {
        this.file = file;
        tokens = new ArrayList<String>();
        parseFile();
    }

    public void parseFile() throws IOException {
        // while not EOF run automata
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)

            // Print the string
            System.out.println(st);
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
