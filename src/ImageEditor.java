import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImageEditor {
    public static void main(String[] args) throws IOException {
        //  Verify edit arguments
        Path inPath = Paths.get(args[0]);

        //  Read in specified file
        Scanner scin = new Scanner(inPath, StandardCharsets.UTF_8);

        //  Put everything in a string
        String fileString = "";
        while(scin.hasNext()){
            String n = scin.next();
            if(n.charAt(0) == '#') {
                scin.nextLine();
                continue;
            }

            fileString += n + " ";

        }

        //  Pass string to PPMImage object
        //  Call correct edit method
        //  Call PPMImage.toString
        //  Write to file

    }
}
