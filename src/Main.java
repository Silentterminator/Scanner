import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input2.txt");

//        if (file.isFile()) {
//            Scanner scan = new Scanner(file);
            // Logic here to scan file

//        }

        Scanner test = new Scanner(file);
        test.parse();
    }
}
