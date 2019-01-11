import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class ImageEditor {

    public static void main(String[] args) throws IOException {
        //  Validate command line arguments
        if (args.length != 3){
            //  Not three arguments
            if (args.length != 4 || args[2] != "motionblur" || !args[3].matches("-?\\d")){
                // If there not exactly 4, or
                // If not "motionblur", or
                // If not an int for motionblurlength, return Usage statement
                System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
                return;
            }

        } else {
            // Exactly three values

            //  Third value must be in this list
            String[] values = {"grayscale","invert","emboss"};
            boolean contains = Arrays.stream(values).anyMatch(args[2]::equals);

            if (!contains){
                //   If not one of the list items, return Usage statement
                System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
                return;
            }
        }

        //  Initialize PPMImage with infile
        PPMImage image = new PPMImage(args[0]);

        switch (args[2]){
            case "grayscale" : {
                image.grayscale();
                break;
            }
            case "invert" : {
                image.invert();
                break;
            }
            case "emboss" : {
                image.emboss();
                break;
            }
            case "motionblur" : {
                image.motionblur(Integer.parseInt(args[3]));
                break;
            }
        }

        image.exportPPM(args[1]);

        return;
    }

}
