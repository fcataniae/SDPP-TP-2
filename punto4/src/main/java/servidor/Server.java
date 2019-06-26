package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port;
	private int cantidadN;
	
	public Server(int port) {
		this.port=port;
		this.startServer();
	}
	
	private void startServer() {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket (this.port);
			System.out.println(" Server started on port: "+this.port);
			
		
		
			while (true) {
				
				Socket client = ss.accept();
				System.out.println("Client connected from port: "+client.getInetAddress().getCanonicalHostName()+" : "+client.getPort());
				BufferedReader inputChannel = new BufferedReader (new InputStreamReader (client.getInputStream()));
				String msg = inputChannel.readLine();
				System.out.println("Cantidad de nodos "+msg);
				this.cantidadN = Integer.parseInt(msg);
				try {
					if (this.cantidadN == 4) {
						ServerImplementa serverI = new ServerImplementa();
						ServerImplementa serverI1 = new ServerImplementa();
						ServerImplementa serverI2 = new ServerImplementa();
						ServerImplementa serverI3 = new ServerImplementa();
						Registry registro = LocateRegistry.createRegistry(7001);
						Registry registro1 = LocateRegistry.createRegistry(7002);
						Registry registro2 = LocateRegistry.createRegistry(7003);
						Registry registro3 = LocateRegistry.createRegistry(7004);
						IRemote remotoS1 = (IRemote) UnicastRemoteObject.exportObject(serverI,5003);
						IRemote remotoS2 = (IRemote) UnicastRemoteObject.exportObject(serverI1,5004);
						IRemote remotoS3 = (IRemote) UnicastRemoteObject.exportObject(serverI2,5005);
						IRemote remotoS4 = (IRemote) UnicastRemoteObject.exportObject(serverI3,5006);
						registro.rebind("Sobel", remotoS1);
						registro1.rebind("Sobel", remotoS2);
						registro2.rebind("Sobel", remotoS3);
						registro3.rebind("Sobel", remotoS4);
					}
					if (this.cantidadN == 9) {
						
						ServerImplementa serverI4 = new ServerImplementa();
						ServerImplementa serverI5 = new ServerImplementa();
						ServerImplementa serverI6 = new ServerImplementa();
						ServerImplementa serverI7 = new ServerImplementa();
						ServerImplementa serverI8 = new ServerImplementa();
						ServerImplementa serverI9= new ServerImplementa();
						ServerImplementa serverI10 = new ServerImplementa();
						ServerImplementa serverI11 = new ServerImplementa();
						ServerImplementa serverI12 = new ServerImplementa();
						Registry registro4 = LocateRegistry.createRegistry(7001);
						Registry registro5 = LocateRegistry.createRegistry(7002);
						Registry registro6 = LocateRegistry.createRegistry(7003);
						Registry registro7 = LocateRegistry.createRegistry(7004);
						Registry registro8 = LocateRegistry.createRegistry(7005);
						Registry registro9 = LocateRegistry.createRegistry(7006);
						Registry registro10 = LocateRegistry.createRegistry(7007);
						Registry registro11 = LocateRegistry.createRegistry(7008);
						Registry registro12 = LocateRegistry.createRegistry(7009);
						IRemote remotoS5 = (IRemote) UnicastRemoteObject.exportObject(serverI4,5003);
						IRemote remotoS6 = (IRemote) UnicastRemoteObject.exportObject(serverI5,5004);
						IRemote remotoS7 = (IRemote) UnicastRemoteObject.exportObject(serverI6,5005);
						IRemote remotoS8 = (IRemote) UnicastRemoteObject.exportObject(serverI7,5006);
						IRemote remotoS9 = (IRemote) UnicastRemoteObject.exportObject(serverI8,5007);
						IRemote remotoS10 = (IRemote) UnicastRemoteObject.exportObject(serverI9,5008);
						IRemote remotoS11 = (IRemote) UnicastRemoteObject.exportObject(serverI10,5009);
						IRemote remotoS12 = (IRemote) UnicastRemoteObject.exportObject(serverI11,5010);
						IRemote remotoS13 = (IRemote) UnicastRemoteObject.exportObject(serverI12,5011);
						registro5.rebind("Sobel", remotoS5);
						registro6.rebind("Sobel", remotoS6);
						registro7.rebind("Sobel", remotoS7);
						registro8.rebind("Sobel", remotoS8);
						registro9.rebind("Sobel", remotoS9);
						registro10.rebind("Sobel", remotoS10);
						registro11.rebind("Sobel", remotoS11);
						registro12.rebind("Sobel", remotoS12);
						registro4.rebind("Sobel", remotoS13);
						
						
					}
					
					if (this.cantidadN == 16) {
						ServerImplementa serverI = new ServerImplementa();
						ServerImplementa serverI1 = new ServerImplementa();
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
						Registry registro = LocateRegistry.createRegistry(7001);
						Registry registro1 = LocateRegistry.createRegistry(7002);
						Registry registro2 = LocateRegistry.createRegistry(7003);
						Registry registro3 = LocateRegistry.createRegistry(7004);
						Registry registro4 = LocateRegistry.createRegistry(7001);
						Registry registro5 = LocateRegistry.createRegistry(7002);
						Registry registro6 = LocateRegistry.createRegistry(7003);
						Registry registro7 = LocateRegistry.createRegistry(7004);
						Registry registro8 = LocateRegistry.createRegistry(7005);
						Registry registro9 = LocateRegistry.createRegistry(7006);
						Registry registro10 = LocateRegistry.createRegistry(7007);
						Registry registro11 = LocateRegistry.createRegistry(7008);
						Registry registro12 = LocateRegistry.createRegistry(7009);
						Registry registro13 = LocateRegistry.createRegistry(7007);
						Registry registro14 = LocateRegistry.createRegistry(7008);
						Registry registro15 = LocateRegistry.createRegistry(7009);
						IRemote remotoS1 = (IRemote) UnicastRemoteObject.exportObject(serverI,5003);
						IRemote remotoS2 = (IRemote) UnicastRemoteObject.exportObject(serverI1,5004);
						IRemote remotoS3 = (IRemote) UnicastRemoteObject.exportObject(serverI2,5005);
						IRemote remotoS4 = (IRemote) UnicastRemoteObject.exportObject(serverI3,5006);
						IRemote remotoS5 = (IRemote) UnicastRemoteObject.exportObject(serverI4,5003);
						IRemote remotoS6 = (IRemote) UnicastRemoteObject.exportObject(serverI5,5004);
						IRemote remotoS7 = (IRemote) UnicastRemoteObject.exportObject(serverI6,5005);
						IRemote remotoS8 = (IRemote) UnicastRemoteObject.exportObject(serverI7,5006);
						IRemote remotoS9 = (IRemote) UnicastRemoteObject.exportObject(serverI8,5007);
						IRemote remotoS10 = (IRemote) UnicastRemoteObject.exportObject(serverI9,5008);
						IRemote remotoS11 = (IRemote) UnicastRemoteObject.exportObject(serverI10,5009);
						IRemote remotoS12 = (IRemote) UnicastRemoteObject.exportObject(serverI11,5010);
						IRemote remotoS13 = (IRemote) UnicastRemoteObject.exportObject(serverI12,5011);
						IRemote remotoS14 = (IRemote) UnicastRemoteObject.exportObject(serverI13,5009);
						IRemote remotoS15 = (IRemote) UnicastRemoteObject.exportObject(serverI14,5010);
						IRemote remotoS16 = (IRemote) UnicastRemoteObject.exportObject(serverI15,5011);
						registro.rebind("Sobel", remotoS1);
						registro1.rebind("Sobel", remotoS2);
						registro2.rebind("Sobel", remotoS3);
						registro3.rebind("Sobel", remotoS4);
						registro5.rebind("Sobel", remotoS5);
						registro6.rebind("Sobel", remotoS6);
						registro7.rebind("Sobel", remotoS7);
						registro8.rebind("Sobel", remotoS8);
						registro9.rebind("Sobel", remotoS9);
						registro10.rebind("Sobel", remotoS10);
						registro11.rebind("Sobel", remotoS11);
						registro12.rebind("Sobel", remotoS12);
						registro4.rebind("Sobel", remotoS13);
						registro13.rebind("Sobel", remotoS14);
						registro14.rebind("Sobel", remotoS15);
						registro15.rebind("Sobel", remotoS16);
					}				
					}catch(Exception e) {
						e.printStackTrace();
					}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(" port in use");
		} 
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server servidor = new Server(5000);
				
		
	}

}
