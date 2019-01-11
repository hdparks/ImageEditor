import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ImageEditor {

    public static void main(String[] args) throws IOException {
        //  Validate command line arguments
        if (args.length != 3){
            //  motion blur takes 4
            if (args.length != 4){
                System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
                return;
            }
        }


        PPMImage image = new PPMImage(args[0]);
        image.exportPPM(args[1]);
    }
}
