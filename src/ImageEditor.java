import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class ImageEditor {

    public static void displayUsageStatement(){
        System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
    }

    public static void main(String[] args) throws IOException {
        //  Validate command line arguments
        if (args.length != 3){
            //  Not three arguments

            if(args.length != 4 || !args[2].equals("motionblur")){
                // If not exactly 4 AND 3rd == motionblur,
                System.out.println();
                System.out.println("ERROR: Incorrect number of arguments");
                ImageEditor.displayUsageStatement();
                return;
            }

            if (Integer.parseInt(args[3]) <= 0){
                // If not an int for motionblurlength, return Usage statement
                System.out.println();
                System.out.println("ERROR: motion-blur-length must be a positive, non-zero integer.");
                ImageEditor.displayUsageStatement();
                return;
            }

        } else {
            // Exactly three values
            //  Third value must be in this list
            String[] values = {"grayscale","invert","emboss"};
            boolean contains = Arrays.asList(values).contains(args[2]);

            if (!contains){
                //   If not one of the list items, return Usage statement
                System.out.println();
                ImageEditor.displayUsageStatement();
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
