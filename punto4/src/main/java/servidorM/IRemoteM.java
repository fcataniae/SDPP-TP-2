package servidorM;

import java.io.IOException;
import java.rmi.Remote;

public interface IRemoteM extends Remote {
	
	public void SobelRemoto(String nImagen, int tamaño) throws IOException;

}
