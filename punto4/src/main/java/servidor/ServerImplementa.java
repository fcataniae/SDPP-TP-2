package servidor;

import java.io.IOException;

import centralizado.SobelMain;

public class ServerImplementa implements IRemote{

	public void SobelRemoto(String nImagen, int tamaño) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(nImagen);
		SobelMain Sobel = new SobelMain(nImagen,tamaño);}
}
