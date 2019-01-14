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

        out.write(this.width+"");
        out.newLine();

        out.write(this.height+"");
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

                Pixel p = this.pixels[i][j];
                Pixel o = this.pixels[i-1][j-1];

                int rdiff = p.r - o.r;
                int gdiff = p.g - o.g;
                int bdiff = p.b - o.b;

                int max = Math.max(Math.max(Math.abs(rdiff),Math.abs(gdiff)),Math.abs(bdiff));

                int v;

                if (Math.abs(rdiff) == max){
                    // Red wins
                    v = 128 + rdiff;
                    v = PPMImage.clip(v);
                    p.r = p.g = p.b = v;

                } else if (Math.abs(gdiff) == max){
                    // Green wins
                    v = 128 + gdiff;
                    v = PPMImage.clip(v);
                    p.r = p.g = p.b = v;
                } else {
                    // Blue wins
                    v = 128 + bdiff;
                    v = PPMImage.clip(v);
                    p.r = p.g = p.b = v;
                }
            }
        }
        //  Deal with left and upper edge
        for (int j = 0; j < this.height; j++){
            Pixel p = this.pixels[j][0];
            p.r = 128;
            p.g = 128;
            p.b = 128;
        }

        for (int i = 1; i < this.width; i++){
            Pixel p = this.pixels[0][i];
            p.r = 128;
            p.g = 128;
            p.b = 128;
        }

        return;

    }

    public void motionblur(int blurLength){
        for (int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){

                int rtot = 0;
                int gtot = 0;
                int btot = 0;
                int i = 0;

                for (int c = x; c < x + blurLength && c < this.width; c++){
                    rtot += this.pixels[y][c].r;
                    gtot += this.pixels[y][c].g;
                    btot += this.pixels[y][c].b;
                    i += 1;
                }

                this.pixels[y][x].r = Math.round((float)rtot / i);
                this.pixels[y][x].g = Math.round((float)gtot / i);
                this.pixels[y][x].b = Math.round((float)btot / i);
            }
        }
        return;
    }

    public static int clip(int v){
        if (v < 0){
            return 0;
        } else if (v > 255){
            return 255;
        } else {
            return v;
        }
    }

}
