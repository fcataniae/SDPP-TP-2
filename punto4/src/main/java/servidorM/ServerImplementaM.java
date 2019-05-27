package servidorM;

import java.io.IOException;
import java.util.Random;

import centralizado.SobelMain;

public class ServerImplementaM implements IRemoteM{

	public void SobelRemoto(String nImagen, int tamaño) throws IOException {
		// TODO Auto-generated method stub
		 Random r = new Random();
		 int prob = Math.abs(r.nextInt()) % 11;
		 if (prob == 10) {
			 try {
				Thread.sleep(5000);
				System.out.println("inactivo "+nImagen);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else {
		SobelMain Sobel = new SobelMain(nImagen,tamaño);}
	}
}
