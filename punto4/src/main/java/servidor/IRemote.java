package servidor;

import java.io.IOException;
import java.rmi.Remote;

public interface IRemote extends Remote {
	
	public void SobelRemoto(String nImagen, int tamaño) throws IOException;

}
