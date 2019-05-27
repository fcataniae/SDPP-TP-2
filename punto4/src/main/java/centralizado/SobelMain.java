package centralizado;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SobelMain {

    private static String sInput; 
    private static final String sReflect = "Reflection.png"; 
    
    private static final int padding_x = 15; // x must be odd integer
    private static final int padding_y = 15; // y must be odd integer
    
    
    /*--------------------------------------------------------------------------------------------*/
    public SobelMain(String sInput,int tamaño) throws IOException {
        SobelMain.sInput=sInput;
        /*--------------- Reflection Padding --------------------*/
        int[][] iimage = ImageRead(sInput);
        Reflection ref = new Reflection();
        
        BufferedImage imagen = ImageIO.read(new File(sInput));
                
        int[][] oimage = ref.conv(iimage, padding_x, padding_y);  
        ImageWrite(oimage, sReflect);
        /*---------------- Sobel Edge Detection -----------------*/
        Sobel sobel = new Sobel();
        sobel.process(sReflect);
       
        /*----------------------- Sobel Magnitute -----------------------------*/
        int[][] temp1 = ref.ScaleDown(sobel.Magnitute, padding_x, padding_x,tamaño);
        ImageWrite(temp1, sInput);
        
	}



	public static int[][] ImageRead(String filename) throws IOException {
        
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
    }
    /*--------------------------------------------------------------------------------------------*/
    public static BufferedImage ImageWrite(int img[][], String filename) throws IOException {

            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }
            if (filename == "imagen.png") {
            	filename = "imagensobel.png";
            }
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
            return bi;
    }
    
   
    /*--------------------------------------------------------------------------------------------*/     
}