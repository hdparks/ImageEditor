import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PPMImage {

    String magicNum;
    int width;
    int height;
    int maxVal;

    Pixel[][] pixels;


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
        this.pixels = new Pixel[this.height][this.width];

        //  Populate pixel array
        int i = 0;
        int j = 0;
        while(scin.hasNext()){
            int r = scin.nextInt();
            int g = scin.nextInt();
            int b = scin.nextInt();

            this.pixels[i][j] = new Pixel(r,g,b);
            if (j == this.width-1){
                i++;
                j = 0;
            } else {
                j++;
            }
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
        for (Pixel[] row : this.pixels){
            for (Pixel p : row){
                out.write(p.r+"");
                out.newLine();

                out.write(p.g+"");
                out.newLine();

                out.write(p.b+"");
                out.newLine();
            }


        }
        //  Close Writer
        out.close();
    }

    public void grayscale(){
        for (Pixel[] row : this.pixels){
            for (Pixel p : row){
                int avg = (p.r + p.g + p.b) / 3;
                p.r = p.g = p.b = avg;
            }
        }
    }

    public void invert(){
        for (Pixel[] row : this.pixels){
            for (Pixel p : row){
                p.r = this.maxVal - p.r;
                p.g = this.maxVal - p.g;
                p.b = this.maxVal - p.b;
            }
        }
    }

    public void emboss(){
        //  Work from bottom right across, then up the rows
        for (int i = this.height - 1; i > 0; i--){
            for (int j = this.width - 1; j > 0; j--){
                Pixel p = image[i][j];
                Pixel o = image[i-1][j-1];

                int rdiff = p.r - o.r;
                int gdiff = p.g - o.g;
                int bdiff = p.b - o.b;

            }
        }
    }

    public void motionblur(int blurLength){

    }

}
