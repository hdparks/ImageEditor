import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PPMImage {

    String magicNum;
    int width;
    int height;
    int maxVal;

    Pixel[] pixels;


    PPMImage(String fileName) throws IOException {
        //  Read in specified file

        Scanner scin = new Scanner(new File(fileName), StandardCharsets.UTF_8);

        //  Configure delimiter to skip comments
        scin.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

        //  Read in magicNum, width, height, maxVal
        this.magicNum = scin.next();
        this.width = scin.nextInt();
        this.height = scin.nextInt();
        this.maxVal = scin.nextInt();

        //  Initialize pixel array
        this.pixels = new Pixel[this.width * this.height];

        //  Populate pixel array
        int i = 0;
        while(scin.hasNext()){
            int r = scin.nextInt();
            int g = scin.nextInt();
            int b = scin.nextInt();

            this.pixels[i] = new Pixel(r,g,b);
            i += 1;
        }
    }

    public void exportPPM(String filename) throws IOException {

        //  Construct Writer object
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));

        // Write header values
        out.write(this.magicNum+"");
        out.newLine();

        out.write(this.height+"");
        out.newLine();

        out.write(this.width+"");
        out.newLine();

        out.write(this.maxVal+"");
        out.newLine();

        //  Write pixel values
        for (Pixel p : this.pixels){

            out.write(p.r+"");
            out.newLine();

            out.write(p.g+"");
            out.newLine();

            out.write(p.b+"");
            out.newLine();
        }
        //  Close Writer
        out.close();
    }



}
