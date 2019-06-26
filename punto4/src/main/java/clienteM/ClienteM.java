package clienteM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cliente.Cliente;
import servidor.IRemote;
import servidorM.IRemoteM;

public class ClienteM {

	public ClienteM(String ip,int port,String opcion) {
		try {
			Socket s = new Socket (ip, port);
			System.out.println(" Client connection ok");
			BufferedReader inputChannel = new BufferedReader (new InputStreamReader (s.getInputStream()));
			PrintWriter outputChannel = new PrintWriter (s.getOutputStream(),true);
			outputChannel.println (opcion);
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Inactivo(String nombre,int tamaño,int puerto) {
		Registry registroaux;
		try {
			if (puerto == 7001) {
				puerto = 7002;
			}else {
				puerto = 7001;
			}
			System.out.println("cambio por caido");
			registroaux = LocateRegistry.getRegistry("localhost",puerto);
			IRemoteM funcionaux = (IRemoteM) registroaux.lookup("Sobel");
			funcionaux.SobelRemoto(nombre, tamaño);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void dividir(int cantidad,BufferedImage imagen) throws IOException, NotBoundException {
		int i;
		int j;
		Registry registro;
		IRemoteM funcion;
		int altura = imagen.getHeight();
		int ancho = imagen.getWidth();
		
		int div = (int) Math.sqrt(cantidad);
		
		int height = div;
		int width = div;
		int alt = altura/div;
		int anc = ancho/div;
		
		int numero = 0;
		int port = 7001;
		for (i=0;i<(height);i++) {
			for (j=0;j<(width);j++) {
				
				
				BufferedImage imagent = imagen.getSubimage(alt*i, anc*j, alt, anc);
				File outputfile = new File("corte"+numero+".png");
				
		        ImageIO.write(imagent,"png", outputfile);
		        numero++;
			}
		}
		long startTime = System.nanoTime();
		for (i=0;i<cantidad;i++) {
			long tiempoauxfin;
			long tiempoaux = System.nanoTime();
			registro = LocateRegistry.getRegistry("localhost",port);
			
			
			
			port = port++;
			funcion = (IRemoteM) registro.lookup("Sobel");
			
			String nombre = "corte"+i+".png";
			System.out.println(nombre);
			funcion.SobelRemoto(nombre, 0); 
			tiempoauxfin = System.nanoTime() - tiempoaux;
			if (tiempoauxfin >2144444444) {
				Inactivo(nombre, port, 0);
			}
		}
		long endTime = System.nanoTime() - startTime;
		double tiempo = (double) endTime / 1000000000;
		System.out.println("tiempo de ejecución: "+tiempo+ " segundos");
			
		BufferedImage combinada = new BufferedImage(altura, ancho, BufferedImage.TYPE_INT_ARGB);
		Graphics g = combinada.getGraphics();
		BufferedImage imagent;
		numero = 0;
		for (i=0;i<div;i++) {
			for (j=0;j<(div);j++) {
			g.drawImage(imagent = ImageIO.read(new File("corte"+numero+".png")), alt*i, anc*j, null);
			numero++;
			}
		}
		File outputfilec = new File("combinada"+cantidad+".png");
        ImageIO.write(combinada,"PNG",outputfilec);
		
	}

	
	
	public static void main(String[] args) {
	
		
	try {
			
			BufferedImage imagen = ImageIO.read(new File("imagen.png"));
			Scanner sc = new Scanner(System.in);
			Cliente client;
			String cant="";
			String opcion = "";
			boolean salir = false;
			while(!salir) {
				System.out.println("Elija una opcion:");
				System.out.println("1- 4 NODOS 4 CORTES\n2- 9 NODOS 9 CORTES\n3- 16 NODOS 16 CORTES\n4 - Salir");
			
				opcion = sc.nextLine();
				switch(opcion) {
				case "1":	
					cant = "4";
					client = new Cliente("localhost",5000,cant);
					dividir(4, imagen);
					
					break;
				case "2": 
					cant = "9";
					client = new Cliente("localhost",5000,cant);
					dividir(9, imagen);
					
					break;
				case "3": 
					cant = "16";
					client = new Cliente("localhost",5000,cant);
					dividir(16, imagen);
					
					break;
				case "4":
					
					System.out.println("FIN");
					salir = true;
				break;
				default:
					System.out.println("Elija 1,2,3 o 4.\n");
					break;
			}
			
				
			


			}
			
			}catch(Exception e) {
			e.printStackTrace();
		}

	}}
