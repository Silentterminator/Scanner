import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input2.txt");

        // If file exists, we can scan it
        if (file.isFile()) {
            Scanner scan = new Scanner(file);
            scan.parse();
        }
    }
}
