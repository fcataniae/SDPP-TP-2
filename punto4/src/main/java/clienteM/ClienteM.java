package clienteM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import servidorM.IRemoteM;

public class ClienteM {

	public static void Inactivo(String nombre,int tamaño,int puerto) {
		Registry registroaux;
		try {
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
	
	
	public static void main(String[] args) {
		
		try {
			
			BufferedImage imagen = ImageIO.read(new File("imagen.png"));
	        
			//DIVISION DE IMAGEN EN 4
			System.out.println("4 NODOS");
			BufferedImage imagen2 = imagen.getSubimage(0, 0, 200, 200);
	        File outputfile = new File("corte.png");
	        ImageIO.write(imagen2,"png", outputfile);
	        
	        BufferedImage imagen3 = imagen.getSubimage(0, 200, 200, 200);
	        File outputfile3 = new File("corte1.png");
	        ImageIO.write(imagen3,"png", outputfile3);
	        
	        BufferedImage imagen4 = imagen.getSubimage(200, 0, 200, 200);
	        File outputfile4 = new File("corte2.png");
	        ImageIO.write(imagen4,"png", outputfile4);
	        
	        BufferedImage imagen5 = imagen.getSubimage(200, 200, 200, 200);
	        File outputfile5 = new File("corte3.png");
	        ImageIO.write(imagen5,"png", outputfile5);
	        
	        //4 NODOS
	       

			Registry registro = LocateRegistry.getRegistry("localhost",4001);
			IRemoteM funcion = (IRemoteM) registro.lookup("Sobel");
			
			Registry registro1 = LocateRegistry.getRegistry("localhost",4002);
			IRemoteM funcion1 = (IRemoteM) registro1.lookup("Sobel");
			
			Registry registro2 = LocateRegistry.getRegistry("localhost",4003);
			IRemoteM funcion2 = (IRemoteM) registro2.lookup("Sobel");
			
			Registry registro3 = LocateRegistry.getRegistry("localhost",4004);
			IRemoteM funcion3 = (IRemoteM) registro3.lookup("Sobel");
			
			
			
			long startTime = System.nanoTime();
			long tiempoaux = System.nanoTime();
			funcion.SobelRemoto("corte.png", 200);
			long tiempoauxfin = System.nanoTime() - tiempoaux;
			if (tiempoauxfin >1000000000) {
				Inactivo("corte.png", 200, 4002);
			}
			startTime = System.nanoTime();
			funcion1.SobelRemoto("corte1.png", 200);
			tiempoauxfin = System.nanoTime() - tiempoaux;
			
			if (tiempoauxfin >1000000000) {
				Inactivo("corte1.png", 200, 4003);
			}
			startTime = System.nanoTime();
			funcion2.SobelRemoto("corte2.png", 200);
			tiempoauxfin = System.nanoTime() - tiempoaux;
			if (tiempoauxfin >1000000000) {
				Inactivo("corte2.png", 200, 4004);
			}
			startTime = System.nanoTime();
			funcion3.SobelRemoto("corte3.png", 200);
			tiempoauxfin = System.nanoTime() - tiempoaux;
			if (tiempoauxfin >1000000000) {
				Inactivo("corte3.png", 200, 4005);
			}
			
			long endTime = System.nanoTime() - startTime;
			double tiempo = (double) endTime / 1000000000;
			System.out.println("tiempo de ejecución: "+tiempo+ " segundos");
			
			//UNION
			
			imagen2 = ImageIO.read(new File("corte.png"));
			imagen3 = ImageIO.read(new File("corte1.png"));
			imagen4 = ImageIO.read(new File("corte2.png"));
			imagen5 = ImageIO.read(new File("corte3.png"));
			
			BufferedImage combinada = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
			startTime = System.nanoTime();
			Graphics g = combinada.getGraphics(); 
	        g.drawImage(imagen2, 0, 0, null); 
	        g.drawImage(imagen3, 0, 200, null);
	        g.drawImage(imagen4,200,0,null);
	        g.drawImage(imagen5,200,200,null);
	        
	        File outputfilec = new File("combinada.png");
	        ImageIO.write(combinada,"PNG",outputfilec);
	        endTime = System.nanoTime() - startTime;
			double tiempoU = (double) endTime / 1000000000;
			System.out.println("tiempo de unión: "+tiempoU+ " segundos");
			double tiempoT = tiempo + tiempoU;
			System.out.println("tiempo total: "+tiempoT+" segundos");
			
			//DIVISION IMAGEN EN 16
			System.out.println("16 NODOS");
			BufferedImage imagen6 = imagen.getSubimage(0, 0, 100, 100);
	        File outputfile6 = new File("corte4.png");
	        ImageIO.write(imagen6,"png", outputfile6);
	        
	        BufferedImage imagen7 = imagen.getSubimage(0, 100, 100, 100);
	        File outputfile7 = new File("corte5.png");
	        ImageIO.write(imagen7,"png", outputfile7);
	        
	        BufferedImage imagen8 = imagen.getSubimage(0, 200, 100, 100);
	        File outputfile8 = new File("corte6.png");
	        ImageIO.write(imagen8,"png", outputfile8);
	        
	        BufferedImage imagen9 = imagen.getSubimage(0, 300, 100, 100);
	        File outputfile9 = new File("corte7.png");
	        ImageIO.write(imagen9,"png", outputfile9);
	        
	        BufferedImage imagen10 = imagen.getSubimage(100, 0, 100, 100);
	        File outputfile10 = new File("corte8.png");
	        ImageIO.write(imagen10,"png", outputfile10);
	        
	        BufferedImage imagen11 = imagen.getSubimage(100, 100, 100, 100);
	        File outputfile11 = new File("corte9.png");
	        ImageIO.write(imagen11,"png", outputfile11);
	        
	        BufferedImage imagen12 = imagen.getSubimage(100, 200, 100, 100);
	        File outputfile12 = new File("corte10.png");
	        ImageIO.write(imagen12,"png", outputfile12);
	        
	        BufferedImage imagen13 = imagen.getSubimage(100, 300, 100, 100);
	        File outputfile13 = new File("corte11.png");
	        ImageIO.write(imagen13,"png", outputfile13);
	        
	        BufferedImage imagen14 = imagen.getSubimage(200, 0, 100, 100);
	        File outputfile14 = new File("corte12.png");
	        ImageIO.write(imagen14,"png", outputfile14);
	        
	        BufferedImage imagen15 = imagen.getSubimage(200, 100, 100, 100);
	        File outputfile15 = new File("corte13.png");
	        ImageIO.write(imagen15,"png", outputfile15);
	        
	        BufferedImage imagen16 = imagen.getSubimage(200, 200, 100, 100);
	        File outputfile16 = new File("corte14.png");
	        ImageIO.write(imagen16,"png", outputfile16);
	        
	        BufferedImage imagen17 = imagen.getSubimage(200, 300, 100, 100);
	        File outputfile17 = new File("corte15.png");
	        ImageIO.write(imagen17,"png", outputfile17);
	        
	        BufferedImage imagen18 = imagen.getSubimage(300, 0, 100, 100);
	        File outputfile18 = new File("corte16.png");
	        ImageIO.write(imagen18,"png", outputfile18);
	        
	        BufferedImage imagen19 = imagen.getSubimage(300, 100, 100, 100);
	        File outputfile19 = new File("corte17.png");
	        ImageIO.write(imagen19,"png", outputfile19);
	        
	        BufferedImage imagen20 = imagen.getSubimage(300, 200, 100, 100);
	        File outputfile20 = new File("corte18.png");
	        ImageIO.write(imagen20,"png", outputfile20);
	        
	        BufferedImage imagen21 = imagen.getSubimage(300, 300, 100, 100);
	        File outputfile21 = new File("corte19.png");
	        ImageIO.write(imagen21,"png", outputfile21);
	        
	        
	        //16 NODOS
			Registry registro4 = LocateRegistry.getRegistry("localhost",4001);
			IRemoteM funcion4 = (IRemoteM) registro4.lookup("Sobel");
			
			Registry registro5 = LocateRegistry.getRegistry("localhost",4002);
			IRemoteM funcion5 = (IRemoteM) registro5.lookup("Sobel");
			
			Registry registro6 = LocateRegistry.getRegistry("localhost",4003);
			IRemoteM funcion6 = (IRemoteM) registro6.lookup("Sobel");
			
			Registry registro7 = LocateRegistry.getRegistry("localhost",4004);
			IRemoteM funcion7 = (IRemoteM) registro7.lookup("Sobel");
			
			Registry registro8 = LocateRegistry.getRegistry("localhost",4005);
			IRemoteM funcion8 = (IRemoteM) registro8.lookup("Sobel");
			
			Registry registro9 = LocateRegistry.getRegistry("localhost",4006);
			IRemoteM funcion9 = (IRemoteM) registro9.lookup("Sobel");
			
			Registry registro10 = LocateRegistry.getRegistry("localhost",4007);
			IRemoteM funcion10 = (IRemoteM) registro10.lookup("Sobel");
			
			Registry registro11 = LocateRegistry.getRegistry("localhost",4008);
			IRemoteM funcion11 = (IRemoteM) registro11.lookup("Sobel");
			
			Registry registro12 = LocateRegistry.getRegistry("localhost",4009);
			IRemoteM funcion12 = (IRemoteM) registro12.lookup("Sobel");
			
			Registry registro13 = LocateRegistry.getRegistry("localhost",4010);
			IRemoteM funcion13 = (IRemoteM) registro13.lookup("Sobel");
			
			Registry registro14 = LocateRegistry.getRegistry("localhost",4011);
			IRemoteM funcion14 = (IRemoteM) registro14.lookup("Sobel");
			
			Registry registro15 = LocateRegistry.getRegistry("localhost",4012);
			IRemoteM funcion15 = (IRemoteM) registro15.lookup("Sobel");
			
			Registry registro16 = LocateRegistry.getRegistry("localhost",4013);
			IRemoteM funcion16 = (IRemoteM) registro16.lookup("Sobel");
			
			Registry registro17 = LocateRegistry.getRegistry("localhost",4014);
			IRemoteM funcion17 = (IRemoteM) registro17.lookup("Sobel");
			
			Registry registro18 = LocateRegistry.getRegistry("localhost",4015);
			IRemoteM funcion18 = (IRemoteM) registro18.lookup("Sobel");
			
			Registry registro19 = LocateRegistry.getRegistry("localhost",4016);
			IRemoteM funcion19 = (IRemoteM) registro19.lookup("Sobel");
			
			startTime = System.nanoTime();
			tiempoaux = System.nanoTime();
			funcion.SobelRemoto("corte4.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte4.png", 100, 4002);
			}
			tiempoaux = System.nanoTime();
			funcion4.SobelRemoto("corte5.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte5.png", 100, 4003);
			}
			
			tiempoaux = System.nanoTime();
			funcion5.SobelRemoto("corte6.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte6.png", 100, 4004);
			}
			
			tiempoaux = System.nanoTime();
			funcion6.SobelRemoto("corte7.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte7.png", 100, 4005);
			}
			
			tiempoaux = System.nanoTime();
			funcion7.SobelRemoto("corte8.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte8.png", 100, 4006);
			}
			
			tiempoaux = System.nanoTime();
			funcion8.SobelRemoto("corte9.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte9.png", 100, 4007);
			}
			
			tiempoaux = System.nanoTime();
			funcion9.SobelRemoto("corte10.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte10.png", 100, 4008);
			}
			
			tiempoaux = System.nanoTime();
			funcion10.SobelRemoto("corte11.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte11.png", 100, 4009);
			}
			
			tiempoaux = System.nanoTime();
			funcion11.SobelRemoto("corte12.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte12.png", 100, 4010);
			}
			
			tiempoaux = System.nanoTime();
			funcion12.SobelRemoto("corte13.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte13.png", 100, 4011);
			}
			
			tiempoaux = System.nanoTime();
			funcion13.SobelRemoto("corte14.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte14.png", 100, 4012);
			}
			
			tiempoaux = System.nanoTime();
			funcion14.SobelRemoto("corte15.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte15.png", 100, 4013);
			}
			
			tiempoaux = System.nanoTime();
			funcion15.SobelRemoto("corte16.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte16.png", 100, 4014);
			}
			
			tiempoaux = System.nanoTime();
			funcion16.SobelRemoto("corte17.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte17.png", 100, 4015);
			}
			
			tiempoaux = System.nanoTime();
			funcion17.SobelRemoto("corte18.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			
			if (tiempoauxfin >100000000) {
				Inactivo("corte18.png", 100, 4016);
			}
			
			tiempoaux = System.nanoTime();
			funcion18.SobelRemoto("corte19.png", 100);
			tiempoauxfin = System.nanoTime() - tiempoaux; 
			if (tiempoauxfin >100000000) {
				Inactivo("corte19.png", 100, 4017);
			}
			
			endTime = System.nanoTime() - startTime;
			tiempo = (double) endTime / 1000000000;
			System.out.println("tiempo de ejecución: "+tiempo+ " segundos");
			
			//UNION
			
			imagen6 = ImageIO.read(new File("corte4.png"));
			imagen7 = ImageIO.read(new File("corte5.png"));
			imagen8 = ImageIO.read(new File("corte6.png"));
			imagen9 = ImageIO.read(new File("corte7.png"));
			imagen10 = ImageIO.read(new File("corte8.png"));
			imagen11 = ImageIO.read(new File("corte9.png"));
			imagen12 = ImageIO.read(new File("corte10.png"));
			imagen13 = ImageIO.read(new File("corte11.png"));
			imagen14 = ImageIO.read(new File("corte12.png"));
			imagen15 = ImageIO.read(new File("corte13.png"));
			imagen16 = ImageIO.read(new File("corte14.png"));
			imagen17 = ImageIO.read(new File("corte15.png"));
			imagen18 = ImageIO.read(new File("corte16.png"));
			imagen19 = ImageIO.read(new File("corte17.png"));
			imagen20 = ImageIO.read(new File("corte18.png"));
			imagen21 = ImageIO.read(new File("corte19.png"));
			
			BufferedImage combinada1 = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
			startTime = System.nanoTime();
			Graphics g1 = combinada1.getGraphics(); 
	        g1.drawImage(imagen6, 0, 0, null); 
	        g1.drawImage(imagen7, 0, 100, null);
	        g1.drawImage(imagen8,0,200,null);
	        g1.drawImage(imagen9,0,300,null);
	        g1.drawImage(imagen10, 100, 0, null); 
	        g1.drawImage(imagen11, 100, 100, null);
	        g1.drawImage(imagen12,100,200,null);
	        g1.drawImage(imagen13,100,300,null);
	        g1.drawImage(imagen14, 200, 0, null); 
	        g1.drawImage(imagen15, 200, 100, null);
	        g1.drawImage(imagen16,200,200,null);
	        g1.drawImage(imagen17,200,300,null);
	        g1.drawImage(imagen18, 300, 0, null); 
	        g1.drawImage(imagen19, 300, 100, null);
	        g1.drawImage(imagen20,300,200,null);
	        g1.drawImage(imagen21,300,300,null);
	        File outputfilec1 = new File("combinada1.png");
	        ImageIO.write(combinada1,"PNG",outputfilec1);
	        endTime = System.nanoTime() - startTime;
			tiempoU = (double) endTime / 1000000000;
			System.out.println("tiempo de unión: "+tiempoU+ " segundos");
			tiempoT = tiempo + tiempoU;
			System.out.println("tiempo total: "+tiempoT);

	       //DIVISION DE IMAGEN EN 25
			System.out.println("25 NODOS");
			imagen6 = imagen.getSubimage(0, 0, 80, 80);
	        outputfile6 = new File("corte4.png");
	        ImageIO.write(imagen6,"png", outputfile6);
	        
	        imagen7 = imagen.getSubimage(0, 80, 80, 80);
	        outputfile7 = new File("corte5.png");
	        ImageIO.write(imagen7,"png", outputfile7);
	        
	        imagen8 = imagen.getSubimage(0, 160, 80, 80);
	        outputfile8 = new File("corte6.png");
	        ImageIO.write(imagen8,"png", outputfile8);
	        
	        imagen9 = imagen.getSubimage(0, 240, 80, 80);
	        outputfile9 = new File("corte7.png");
	        ImageIO.write(imagen9,"png", outputfile9);
	        
	        imagen10 = imagen.getSubimage(0, 320, 80, 80);
	        outputfile10 = new File("corte8.png");
	        ImageIO.write(imagen10,"png", outputfile10);
	        
	        imagen11 = imagen.getSubimage(80, 0, 80, 80);
	        outputfile11 = new File("corte9.png");
	        ImageIO.write(imagen11,"png", outputfile11);
	        
	        imagen12 = imagen.getSubimage(80, 80, 80, 80);
	        outputfile12 = new File("corte10.png");
	        ImageIO.write(imagen12,"png", outputfile12);
	        
	        imagen13 = imagen.getSubimage(80, 160, 80, 80);
	        outputfile13 = new File("corte11.png");
	        ImageIO.write(imagen13,"png", outputfile13);
	        
	        imagen14 = imagen.getSubimage(80, 240, 80, 80);
	        outputfile14 = new File("corte12.png");
	        ImageIO.write(imagen14,"png", outputfile14);
	        
	        imagen15 = imagen.getSubimage(80, 320, 80, 80);
	        outputfile15 = new File("corte13.png");
	        ImageIO.write(imagen15,"png", outputfile15);
	        
	        imagen16 = imagen.getSubimage(160, 0, 80, 80);
	        outputfile16 = new File("corte14.png");
	        ImageIO.write(imagen16,"png", outputfile16);
	        
	        imagen17 = imagen.getSubimage(160, 80, 80, 80);
	        outputfile17 = new File("corte15.png");
	        ImageIO.write(imagen17,"png", outputfile17);
	        
	        imagen18 = imagen.getSubimage(160, 160, 80, 80);
	        outputfile18 = new File("corte16.png");
	        ImageIO.write(imagen18,"png", outputfile18);
	        
	        imagen19 = imagen.getSubimage(160, 240, 80, 80);
	        outputfile19 = new File("corte17.png");
	        ImageIO.write(imagen19,"png", outputfile19);
	        
	        imagen20 = imagen.getSubimage(160, 320, 80, 80);
	        outputfile20 = new File("corte18.png");
	        ImageIO.write(imagen20,"png", outputfile20);
	        
	        imagen21 = imagen.getSubimage(240, 0, 80, 80);
	        outputfile21 = new File("corte19.png");
	        ImageIO.write(imagen21,"png", outputfile21);
	        
	        BufferedImage imagen22 = imagen.getSubimage(240, 80, 80, 80);
	        File outputfile22 = new File("corte20.png");
	        ImageIO.write(imagen22,"png", outputfile22);
	        
	        BufferedImage imagen23 = imagen.getSubimage(240, 160, 80, 80);
	        File outputfile23 = new File("corte21.png");
	        ImageIO.write(imagen23,"png", outputfile23);
	        
	        BufferedImage imagen24 = imagen.getSubimage(240, 240, 80, 80);
	        File outputfile24 = new File("corte22.png");
	        ImageIO.write(imagen24,"png", outputfile24);
	        
	        BufferedImage imagen25 = imagen.getSubimage(240, 320, 80, 80);
	        File outputfile25 = new File("corte23.png");
	        ImageIO.write(imagen25,"png", outputfile25);
	        
	        BufferedImage imagen26 = imagen.getSubimage(320, 0, 80, 80);
	        File outputfile26 = new File("corte24.png");
	        ImageIO.write(imagen26,"png", outputfile26);
	        
	        BufferedImage imagen27 = imagen.getSubimage(320, 80, 80, 80);
	        File outputfile27 = new File("corte25.png");
	        ImageIO.write(imagen27,"png", outputfile27);
	        
	        BufferedImage imagen28 = imagen.getSubimage(320, 160, 80, 80);
	        File outputfile28 = new File("corte26.png");
	        ImageIO.write(imagen28,"png", outputfile28);
	        
	        BufferedImage imagen29 = imagen.getSubimage(320, 240, 80, 80);
	        File outputfile29 = new File("corte27.png");
	        ImageIO.write(imagen29,"png", outputfile29);
	        
	        BufferedImage imagen30 = imagen.getSubimage(320, 320, 80, 80);
	        File outputfile30 = new File("corte28.png");
	        ImageIO.write(imagen30,"png", outputfile30);
			
	        
				
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
