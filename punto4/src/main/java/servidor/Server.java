package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			
			ServerImplementa serverI = new ServerImplementa();
			ServerImplementa serverI2 = new ServerImplementa();
			ServerImplementa serverI3 = new ServerImplementa();
			ServerImplementa serverI4 = new ServerImplementa();
			ServerImplementa serverI5 = new ServerImplementa();
			ServerImplementa serverI6 = new ServerImplementa();
			ServerImplementa serverI7 = new ServerImplementa();
			ServerImplementa serverI8 = new ServerImplementa();
			ServerImplementa serverI9= new ServerImplementa();
			ServerImplementa serverI10 = new ServerImplementa();
			ServerImplementa serverI11 = new ServerImplementa();
			ServerImplementa serverI12 = new ServerImplementa();
			ServerImplementa serverI13 = new ServerImplementa();
			ServerImplementa serverI14 = new ServerImplementa();
			ServerImplementa serverI15 = new ServerImplementa();
			ServerImplementa serverI16 = new ServerImplementa();
			ServerImplementa serverI17 = new ServerImplementa();
			ServerImplementa serverI18 = new ServerImplementa();
			ServerImplementa serverI19 = new ServerImplementa();
			ServerImplementa serverI20 = new ServerImplementa();
			ServerImplementa serverI21 = new ServerImplementa();
			ServerImplementa serverI22 = new ServerImplementa();
			ServerImplementa serverI23 = new ServerImplementa();
			ServerImplementa serverI24 = new ServerImplementa();
			ServerImplementa serverI25= new ServerImplementa();
			
			
			Registry registro = LocateRegistry.createRegistry(7001);
			Registry registro1 = LocateRegistry.createRegistry(7002);
			Registry registro2 = LocateRegistry.createRegistry(7003);
			Registry registro3 = LocateRegistry.createRegistry(7004);
			Registry registro4 = LocateRegistry.createRegistry(7005);
			Registry registro5 = LocateRegistry.createRegistry(7006);
			Registry registro6 = LocateRegistry.createRegistry(7007);
			Registry registro7 = LocateRegistry.createRegistry(7008);
			Registry registro8= LocateRegistry.createRegistry(7009);
			Registry registro9 = LocateRegistry.createRegistry(7010);
			Registry registro10 = LocateRegistry.createRegistry(7011);
			Registry registro11 = LocateRegistry.createRegistry(7012);
			Registry registro12 = LocateRegistry.createRegistry(7013);
			Registry registro13 = LocateRegistry.createRegistry(7014);
			Registry registro14 = LocateRegistry.createRegistry(7015);
			Registry registro15 = LocateRegistry.createRegistry(7016);
			Registry registro16 = LocateRegistry.createRegistry(7017);
			Registry registro17 = LocateRegistry.createRegistry(7018);
			Registry registro18 = LocateRegistry.createRegistry(7019);
			Registry registro19 = LocateRegistry.createRegistry(7020);
			Registry registro20 = LocateRegistry.createRegistry(7021);
			Registry registro21 = LocateRegistry.createRegistry(7022);
			Registry registro22 = LocateRegistry.createRegistry(7023);
			Registry registro23 = LocateRegistry.createRegistry(7024);
			Registry registro24 = LocateRegistry.createRegistry(7025);
			
			
			IRemote remotoS = (IRemote) UnicastRemoteObject.exportObject(serverI,5001);
			IRemote remotoS1 = (IRemote) UnicastRemoteObject.exportObject(serverI2,5002);
			IRemote remotoS2 = (IRemote) UnicastRemoteObject.exportObject(serverI3,5003);
			IRemote remotoS3 = (IRemote) UnicastRemoteObject.exportObject(serverI4,5004);
			IRemote remotoS4 = (IRemote) UnicastRemoteObject.exportObject(serverI5,5005);
			IRemote remotoS5 = (IRemote) UnicastRemoteObject.exportObject(serverI6,5006);
			IRemote remotoS6 = (IRemote) UnicastRemoteObject.exportObject(serverI7,5007);
			IRemote remotoS7 = (IRemote) UnicastRemoteObject.exportObject(serverI8,5008);
			IRemote remotoS8 = (IRemote) UnicastRemoteObject.exportObject(serverI9,5009);
			IRemote remotoS9 = (IRemote) UnicastRemoteObject.exportObject(serverI10,5010);
			IRemote remotoS10 = (IRemote) UnicastRemoteObject.exportObject(serverI11,5011);
			IRemote remotoS11 = (IRemote) UnicastRemoteObject.exportObject(serverI12,5012);
			IRemote remotoS12 = (IRemote) UnicastRemoteObject.exportObject(serverI13,5013);
			IRemote remotoS13 = (IRemote) UnicastRemoteObject.exportObject(serverI14,5014);
			IRemote remotoS14 = (IRemote) UnicastRemoteObject.exportObject(serverI15,5015);
			IRemote remotoS15 = (IRemote) UnicastRemoteObject.exportObject(serverI16,5016);
			IRemote remotoS16 = (IRemote) UnicastRemoteObject.exportObject(serverI17,5017);
			IRemote remotoS17 = (IRemote) UnicastRemoteObject.exportObject(serverI18,5018);
			IRemote remotoS18 = (IRemote) UnicastRemoteObject.exportObject(serverI19,5019);
			IRemote remotoS19 = (IRemote) UnicastRemoteObject.exportObject(serverI20,5020);
			IRemote remotoS20 = (IRemote) UnicastRemoteObject.exportObject(serverI21,5021);
			IRemote remotoS21 = (IRemote) UnicastRemoteObject.exportObject(serverI22,5022);
			IRemote remotoS22 = (IRemote) UnicastRemoteObject.exportObject(serverI23,5023);
      		IRemote remotoS23 = (IRemote) UnicastRemoteObject.exportObject(serverI24,5024);
			IRemote remotoS24 = (IRemote) UnicastRemoteObject.exportObject(serverI25,5025);

			registro.rebind("Sobel", remotoS);
			registro1.rebind("Sobel", remotoS1);
			registro2.rebind("Sobel", remotoS2);
			registro3.rebind("Sobel", remotoS3);
			registro4.rebind("Sobel", remotoS4);
			registro5.rebind("Sobel", remotoS5);
			registro6.rebind("Sobel", remotoS6);
			registro7.rebind("Sobel", remotoS7);
			registro8.rebind("Sobel", remotoS8);
			registro9.rebind("Sobel", remotoS9);
			registro10.rebind("Sobel", remotoS10);
			registro11.rebind("Sobel", remotoS11);
			registro12.rebind("Sobel", remotoS12);
			registro13.rebind("Sobel", remotoS13);
			registro14.rebind("Sobel", remotoS14);
			registro15.rebind("Sobel", remotoS15);
			registro16.rebind("Sobel", remotoS16);
			registro17.rebind("Sobel", remotoS17);
			registro18.rebind("Sobel", remotoS18);
			registro19.rebind("Sobel", remotoS19);
			registro20.rebind("Sobel", remotoS20);
			registro21.rebind("Sobel", remotoS21);
			registro22.rebind("Sobel", remotoS22);
			registro23.rebind("Sobel", remotoS23);
			registro24.rebind("Sobel", remotoS24);
			
			
			System.out.println("Servidor escuchando en el puerto 7001 al puerto 7025");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
