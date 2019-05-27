package servidorM;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerM {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			
			ServerImplementaM serverI = new ServerImplementaM();
			ServerImplementaM serverI2 = new ServerImplementaM();
			ServerImplementaM serverI3 = new ServerImplementaM();
			ServerImplementaM serverI4 = new ServerImplementaM();
			ServerImplementaM serverI5 = new ServerImplementaM();
			ServerImplementaM serverI6 = new ServerImplementaM();
			ServerImplementaM serverI7 = new ServerImplementaM();
			ServerImplementaM serverI8 = new ServerImplementaM();
			ServerImplementaM serverI9= new ServerImplementaM();
			ServerImplementaM serverI10 = new ServerImplementaM();
			ServerImplementaM serverI11 = new ServerImplementaM();
			ServerImplementaM serverI12 = new ServerImplementaM();
			ServerImplementaM serverI13 = new ServerImplementaM();
			ServerImplementaM serverI14 = new ServerImplementaM();
			ServerImplementaM serverI15 = new ServerImplementaM();
			ServerImplementaM serverI16 = new ServerImplementaM();
			ServerImplementaM serverI17 = new ServerImplementaM();
			ServerImplementaM serverI18 = new ServerImplementaM();
			ServerImplementaM serverI19 = new ServerImplementaM();
			ServerImplementaM serverI20 = new ServerImplementaM();
			ServerImplementaM serverI21 = new ServerImplementaM();
			ServerImplementaM serverI22 = new ServerImplementaM();
			ServerImplementaM serverI23 = new ServerImplementaM();
			ServerImplementaM serverI24 = new ServerImplementaM();
			ServerImplementaM serverI25= new ServerImplementaM();
			ServerImplementaM serverI26= new ServerImplementaM();
			
			Registry registro = LocateRegistry.createRegistry(4001);
			Registry registro1 = LocateRegistry.createRegistry(4002);
			Registry registro2 = LocateRegistry.createRegistry(4003);
			Registry registro3 = LocateRegistry.createRegistry(4004);
			Registry registro4 = LocateRegistry.createRegistry(4005);
			Registry registro5 = LocateRegistry.createRegistry(4006);
			Registry registro6 = LocateRegistry.createRegistry(4007);
			Registry registro7 = LocateRegistry.createRegistry(4008);
			Registry registro8= LocateRegistry.createRegistry(4009);
			Registry registro9 = LocateRegistry.createRegistry(4010);
			Registry registro10 = LocateRegistry.createRegistry(4011);
			Registry registro11 = LocateRegistry.createRegistry(4012);
			Registry registro12 = LocateRegistry.createRegistry(4013);
			Registry registro13 = LocateRegistry.createRegistry(4014);
			Registry registro14 = LocateRegistry.createRegistry(4015);
			Registry registro15 = LocateRegistry.createRegistry(4016);
			Registry registro16 = LocateRegistry.createRegistry(4017);
			Registry registro17 = LocateRegistry.createRegistry(4018);
			Registry registro18 = LocateRegistry.createRegistry(4019);
			Registry registro19 = LocateRegistry.createRegistry(4020);
			Registry registro20 = LocateRegistry.createRegistry(4021);
			Registry registro21 = LocateRegistry.createRegistry(4022);
			Registry registro22 = LocateRegistry.createRegistry(4023);
			Registry registro23 = LocateRegistry.createRegistry(4024);
			Registry registro24 = LocateRegistry.createRegistry(4025);
			Registry registro25 = LocateRegistry.createRegistry(4026);
			
			
			IRemoteM remotoS = (IRemoteM) UnicastRemoteObject.exportObject(serverI,3001);
			IRemoteM remotoS1 = (IRemoteM) UnicastRemoteObject.exportObject(serverI2,3002);
			IRemoteM remotoS2 = (IRemoteM) UnicastRemoteObject.exportObject(serverI3,3003);
			IRemoteM remotoS3 = (IRemoteM) UnicastRemoteObject.exportObject(serverI4,3004);
			IRemoteM remotoS4 = (IRemoteM) UnicastRemoteObject.exportObject(serverI5,3005);
			IRemoteM remotoS5 = (IRemoteM) UnicastRemoteObject.exportObject(serverI6,3006);
			IRemoteM remotoS6 = (IRemoteM) UnicastRemoteObject.exportObject(serverI7,3007);
			IRemoteM remotoS7 = (IRemoteM) UnicastRemoteObject.exportObject(serverI8,3008);
			IRemoteM remotoS8 = (IRemoteM) UnicastRemoteObject.exportObject(serverI9,3009);
			IRemoteM remotoS9 = (IRemoteM) UnicastRemoteObject.exportObject(serverI10,3010);
			IRemoteM remotoS10 = (IRemoteM) UnicastRemoteObject.exportObject(serverI11,3011);
			IRemoteM remotoS11 = (IRemoteM) UnicastRemoteObject.exportObject(serverI12,3012);
			IRemoteM remotoS12 = (IRemoteM) UnicastRemoteObject.exportObject(serverI13,3013);
			IRemoteM remotoS13 = (IRemoteM) UnicastRemoteObject.exportObject(serverI14,3014);
			IRemoteM remotoS14 = (IRemoteM) UnicastRemoteObject.exportObject(serverI15,3015);
			IRemoteM remotoS15 = (IRemoteM) UnicastRemoteObject.exportObject(serverI16,3016);
			IRemoteM remotoS16 = (IRemoteM) UnicastRemoteObject.exportObject(serverI17,3017);
			IRemoteM remotoS17 = (IRemoteM) UnicastRemoteObject.exportObject(serverI18,3018);
			IRemoteM remotoS18 = (IRemoteM) UnicastRemoteObject.exportObject(serverI19,3019);
			IRemoteM remotoS19 = (IRemoteM) UnicastRemoteObject.exportObject(serverI20,3020);
			IRemoteM remotoS20 = (IRemoteM) UnicastRemoteObject.exportObject(serverI21,3021);
			IRemoteM remotoS21 = (IRemoteM) UnicastRemoteObject.exportObject(serverI22,3022);
			IRemoteM remotoS22 = (IRemoteM) UnicastRemoteObject.exportObject(serverI23,3023);
      		IRemoteM remotoS23 = (IRemoteM) UnicastRemoteObject.exportObject(serverI24,3024);
			IRemoteM remotoS24 = (IRemoteM) UnicastRemoteObject.exportObject(serverI25,3025);
			IRemoteM remotoS25 = (IRemoteM) UnicastRemoteObject.exportObject(serverI26,3026);
			
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
			registro25.rebind("Sobel", remotoS25);
			
			System.out.println("Servidor escuchando en el puerto 4001 al puerto 4025");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
