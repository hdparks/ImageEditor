import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ImageEditor {

    public static void main(String[] args) throws IOException {
        PPMImage image = new PPMImage(args[0]);
        image.exportPPM(args[1]);
    }
}
