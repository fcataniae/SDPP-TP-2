package centralizado;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sobel {
    
    public double[][] Magnitute;

    public void process(String filename) {

        int[][] img = ImageRead(filename);
        int rows = img.length;
        int cols = img[0].length;

        double[][] Gx = new double[rows][cols];
        double[][] Gy = new double[rows][cols];
        double[][] Mag = new double[rows][cols];


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {

                    Gx[i][j] = Gy[i][j] = Mag[i][j] = 0; // Initialize

                } else {

                    Gx[i][j] = img[i + 1][j - 1] + 2 * img[i + 1][j] + img[i + 1][j + 1]
                            - img[i - 1][j - 1] - 2 * img[i - 1][j] - img[i - 1][j + 1];

                    Gy[i][j] = img[i - 1][j + 1] + 2 * img[i][j + 1] + img[i + 1][j + 1]
                            - img[i - 1][j - 1] - 2 * img[i][j - 1] - img[i + 1][j - 1];

                    Mag[i][j] = Math.sqrt(Gx[i][j] * Gx[i][j] + Gy[i][j] * Gy[i][j]);
                                       

                }

            } 

        } 
        Magnitute = ScaleMagnitude(Mag);
       
        
    }

    private double[][] ScaleMagnitude(double[][] Mag) {
        
        double[][] mag = new double[Mag.length][Mag[0].length];
        for (int i = 0; i < Mag.length; i++) {
            for (int j = 0; j < Mag[i].length; j++) {
                    mag[i][j] = (Mag[i][j] * 255) / 1448.154687;
            }
        }
        return mag;
    }
 
    /*--------------------------------------------------------------------------------------------*/

    private int[][] ImageRead(String filename) {

        try {
            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);
           
            int red[][] = new int[bi.getHeight()][bi.getWidth()];
            int grn[][] = new int[bi.getHeight()][bi.getWidth()];
            int blu[][] = new int[bi.getHeight()][bi.getWidth()];
            for (int i = 0; i < red.length; ++i) {
                for (int j = 0; j < red[i].length; ++j) {
                    red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    blu[i][j] = bi.getRGB(j, i) & 0xFF;
                }
            }

            return grn;

        } catch (IOException e) {
            System.out.println("image I/O error");
            return null;
        }
    }
}