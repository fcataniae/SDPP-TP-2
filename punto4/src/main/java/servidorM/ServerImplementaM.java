package servidorM;

import java.io.IOException;
import java.util.Random;

import centralizado.SobelMain;

public class ServerImplementaM implements IRemoteM{

	public void SobelRemoto(String nImagen, int tamaño) throws IOException {
		// TODO Auto-generated method stub
		 Random r = new Random();
		 int prob = Math.abs(r.nextInt()) % 11;
		 
		 if (prob == 9) {
			 try {
				Thread.sleep(5000);
				System.out.println("inactivo "+nImagen);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else {
		 System.out.println("activo");
		SobelMain Sobel = new SobelMain(nImagen,tamaño);}
	}
}
