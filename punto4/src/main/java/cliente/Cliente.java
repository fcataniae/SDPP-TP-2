package cliente;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import servidor.IRemote;

public class Cliente {

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
	       

			Registry registro = LocateRegistry.getRegistry("localhost",7001);
			IRemote funcion = (IRemote) registro.lookup("Sobel");
			
			Registry registro1 = LocateRegistry.getRegistry("localhost",7002);
			IRemote funcion1 = (IRemote) registro1.lookup("Sobel");
			
			Registry registro2 = LocateRegistry.getRegistry("localhost",7003);
			IRemote funcion2 = (IRemote) registro2.lookup("Sobel");
			
			Registry registro3 = LocateRegistry.getRegistry("localhost",7004);
			IRemote funcion3 = (IRemote) registro3.lookup("Sobel");
			
			
			long startTime = System.nanoTime();
			
			funcion.SobelRemoto("corte.png", 200);
			
			funcion1.SobelRemoto("corte1.png", 200);
			
			funcion2.SobelRemoto("corte2.png", 200);
			
			funcion3.SobelRemoto("corte3.png", 200);
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
			System.out.println("tiempo total: "+tiempoT);

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
			Registry registro4 = LocateRegistry.getRegistry("localhost",7001);
			IRemote funcion4 = (IRemote) registro4.lookup("Sobel");
			
			Registry registro5 = LocateRegistry.getRegistry("localhost",7002);
			IRemote funcion5 = (IRemote) registro5.lookup("Sobel");
			
			Registry registro6 = LocateRegistry.getRegistry("localhost",7003);
			IRemote funcion6 = (IRemote) registro6.lookup("Sobel");
			
			Registry registro7 = LocateRegistry.getRegistry("localhost",7004);
			IRemote funcion7 = (IRemote) registro7.lookup("Sobel");
			
			Registry registro8 = LocateRegistry.getRegistry("localhost",7005);
			IRemote funcion8 = (IRemote) registro.lookup("Sobel");
			
			Registry registro9 = LocateRegistry.getRegistry("localhost",7006);
			IRemote funcion9 = (IRemote) registro.lookup("Sobel");
			
			Registry registro10 = LocateRegistry.getRegistry("localhost",7007);
			IRemote funcion10 = (IRemote) registro.lookup("Sobel");
			
			Registry registro11 = LocateRegistry.getRegistry("localhost",7008);
			IRemote funcion11 = (IRemote) registro11.lookup("Sobel");
			
			Registry registro12 = LocateRegistry.getRegistry("localhost",7009);
			IRemote funcion12 = (IRemote) registro12.lookup("Sobel");
			
			Registry registro13 = LocateRegistry.getRegistry("localhost",7010);
			IRemote funcion13 = (IRemote) registro13.lookup("Sobel");
			
			Registry registro14 = LocateRegistry.getRegistry("localhost",7011);
			IRemote funcion14 = (IRemote) registro14.lookup("Sobel");
			
			Registry registro15 = LocateRegistry.getRegistry("localhost",7012);
			IRemote funcion15 = (IRemote) registro15.lookup("Sobel");
			
			Registry registro16 = LocateRegistry.getRegistry("localhost",7013);
			IRemote funcion16 = (IRemote) registro16.lookup("Sobel");
			
			Registry registro17 = LocateRegistry.getRegistry("localhost",7014);
			IRemote funcion17 = (IRemote) registro17.lookup("Sobel");
			
			Registry registro18 = LocateRegistry.getRegistry("localhost",7015);
			IRemote funcion18 = (IRemote) registro18.lookup("Sobel");
			
			Registry registro19 = LocateRegistry.getRegistry("localhost",7016);
			IRemote funcion19 = (IRemote) registro19.lookup("Sobel");
			
			 startTime = System.nanoTime();
			 
			 funcion.SobelRemoto("corte4.png", 100);
			
			funcion4.SobelRemoto("corte5.png", 100);
			
			funcion5.SobelRemoto("corte6.png", 100);
			
			funcion6.SobelRemoto("corte7.png", 100);
			
			funcion7.SobelRemoto("corte8.png", 100);
			
			funcion8.SobelRemoto("corte9.png", 100);
			
			funcion9.SobelRemoto("corte10.png", 100);
			
			funcion10.SobelRemoto("corte11.png", 100);
			
			funcion11.SobelRemoto("corte12.png", 100);
			
			funcion12.SobelRemoto("corte13.png", 100);
			
			funcion13.SobelRemoto("corte14.png", 100);
			
			funcion14.SobelRemoto("corte15.png", 100);
			
			funcion15.SobelRemoto("corte16.png", 100);
			
			funcion16.SobelRemoto("corte17.png", 100);
			
			funcion17.SobelRemoto("corte18.png", 100);
			
			funcion18.SobelRemoto("corte19.png", 100);
			
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
			System.out.println("tiempo total: "+tiempoT+ " segundos");

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
			
	        //25 NODOS
	        
	        registro4 = LocateRegistry.getRegistry("localhost",7001);
			funcion4 = (IRemote) registro4.lookup("Sobel");
			
			registro5 = LocateRegistry.getRegistry("localhost",7002);
			funcion5 = (IRemote) registro5.lookup("Sobel");
			
			registro6 = LocateRegistry.getRegistry("localhost",7003);
			funcion6 = (IRemote) registro6.lookup("Sobel");
			
			registro7 = LocateRegistry.getRegistry("localhost",7004);
			funcion7 = (IRemote) registro7.lookup("Sobel");
			
			registro8 = LocateRegistry.getRegistry("localhost",7005);
			funcion8 = (IRemote) registro8.lookup("Sobel");
			
			registro9 = LocateRegistry.getRegistry("localhost",7006);
			funcion9 = (IRemote) registro9.lookup("Sobel");
			
			registro10 = LocateRegistry.getRegistry("localhost",7007);
			funcion10 = (IRemote) registro10.lookup("Sobel");
			
			registro11 = LocateRegistry.getRegistry("localhost",7008);
			funcion11 = (IRemote) registro11.lookup("Sobel");
			
			registro12 = LocateRegistry.getRegistry("localhost",7009);
			funcion12 = (IRemote) registro12.lookup("Sobel");
			
			registro13 = LocateRegistry.getRegistry("localhost",7010);
			funcion13 = (IRemote) registro13.lookup("Sobel");
			
			registro14 = LocateRegistry.getRegistry("localhost",7011);
			funcion14 = (IRemote) registro14.lookup("Sobel");
			
			registro15 = LocateRegistry.getRegistry("localhost",7012);
			funcion15 = (IRemote) registro15.lookup("Sobel");
			
			registro16 = LocateRegistry.getRegistry("localhost",7013);
			funcion16 = (IRemote) registro16.lookup("Sobel");
			
			registro17 = LocateRegistry.getRegistry("localhost",7014);
			funcion17 = (IRemote) registro17.lookup("Sobel");
			
			registro18 = LocateRegistry.getRegistry("localhost",7015);
			funcion18 = (IRemote) registro18.lookup("Sobel");
			
			registro19 = LocateRegistry.getRegistry("localhost",7016);
			funcion19 = (IRemote) registro19.lookup("Sobel");
			
			Registry registro20 = LocateRegistry.getRegistry("localhost",7017);
			IRemote funcion20 = (IRemote) registro20.lookup("Sobel");
			
			Registry registro21 = LocateRegistry.getRegistry("localhost",7018);
			IRemote funcion21 = (IRemote) registro21.lookup("Sobel");
			
			Registry registro22 = LocateRegistry.getRegistry("localhost",7019);
			IRemote funcion22 = (IRemote) registro22.lookup("Sobel");
			
			Registry registro23 = LocateRegistry.getRegistry("localhost",7020);
			IRemote funcion23 = (IRemote) registro23.lookup("Sobel");
			
			Registry registro24 = LocateRegistry.getRegistry("localhost",7021);
			IRemote funcion24 = (IRemote) registro24.lookup("Sobel");
			
			Registry registro25 = LocateRegistry.getRegistry("localhost",7022);
			IRemote funcion25 = (IRemote) registro25.lookup("Sobel");
			
			Registry registro26 = LocateRegistry.getRegistry("localhost",7023);
			IRemote funcion26 = (IRemote) registro26.lookup("Sobel");
			
			Registry registro27 = LocateRegistry.getRegistry("localhost",7024);
			IRemote funcion27 = (IRemote) registro27.lookup("Sobel");
			
			Registry registro28 = LocateRegistry.getRegistry("localhost",7025);
			IRemote funcion28 = (IRemote) registro28.lookup("Sobel");
			
			 startTime = System.nanoTime();
				funcion.SobelRemoto("corte4.png", 80);
				
				funcion4.SobelRemoto("corte5.png", 80);
				
				funcion5.SobelRemoto("corte6.png", 80);
				
				funcion6.SobelRemoto("corte7.png", 80);
				
				funcion7.SobelRemoto("corte8.png", 80);
				
				funcion8.SobelRemoto("corte9.png", 80);
				
				funcion9.SobelRemoto("corte10.png", 80);
				
				funcion10.SobelRemoto("corte11.png", 80);
				
				funcion11.SobelRemoto("corte12.png", 80);
				
				funcion12.SobelRemoto("corte13.png", 80);
				
				funcion13.SobelRemoto("corte14.png", 80);
				
				funcion14.SobelRemoto("corte15.png", 80);
				
				funcion15.SobelRemoto("corte16.png", 80);
				
				funcion16.SobelRemoto("corte17.png", 80);
				
				funcion17.SobelRemoto("corte18.png", 80);
				
				funcion18.SobelRemoto("corte19.png", 80);
				
				funcion19.SobelRemoto("corte20.png", 80);
				
				funcion20.SobelRemoto("corte21.png", 80);
				
				funcion21.SobelRemoto("corte22.png", 80);
				
				funcion22.SobelRemoto("corte23.png", 80);
				
				funcion23.SobelRemoto("corte24.png", 80);
				
				funcion24.SobelRemoto("corte25.png", 80);
				
				funcion25.SobelRemoto("corte26.png", 80);
				
				funcion26.SobelRemoto("corte27.png", 80);
				
				funcion27.SobelRemoto("corte28.png", 80);
				
				
				
				
				
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
				imagen22 = ImageIO.read(new File("corte20.png"));
				imagen23 = ImageIO.read(new File("corte21.png"));
				imagen24 = ImageIO.read(new File("corte22.png"));
				imagen25 = ImageIO.read(new File("corte23.png"));
				imagen26 = ImageIO.read(new File("corte24.png"));
				imagen27 = ImageIO.read(new File("corte25.png"));
				imagen28 = ImageIO.read(new File("corte26.png"));
				imagen29 = ImageIO.read(new File("corte27.png"));
				imagen30 = ImageIO.read(new File("corte28.png"));
				
				
				BufferedImage combinada2 = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
				startTime = System.nanoTime();
				g1 = combinada2.getGraphics(); 
		        g1.drawImage(imagen6, 0, 0, null); 
		        g1.drawImage(imagen7, 0, 80, null);
		        g1.drawImage(imagen8,0,160,null);
		        g1.drawImage(imagen9,0,240,null);
		        g1.drawImage(imagen10, 0, 320, null); 
		        g1.drawImage(imagen11, 80, 0, null);
		        g1.drawImage(imagen12,80,80,null);
		        g1.drawImage(imagen13,80,160,null);
		        g1.drawImage(imagen14, 80, 240, null); 
		        g1.drawImage(imagen15, 80, 320, null);
		        g1.drawImage(imagen16,160,0,null);
		        g1.drawImage(imagen17,160,80,null);
		        g1.drawImage(imagen18, 160, 160, null); 
		        g1.drawImage(imagen19, 160, 240, null);
		        g1.drawImage(imagen20,160,320,null);
		        g1.drawImage(imagen21,240,0,null);
		        g1.drawImage(imagen22,240,80,null);
		        g1.drawImage(imagen23,240,160,null);
		        g1.drawImage(imagen24,240,240,null);
		        g1.drawImage(imagen25,240,320,null);
		        g1.drawImage(imagen26,320,0,null);
		        g1.drawImage(imagen27,320,80,null);
		        g1.drawImage(imagen28,320,160,null);
		        g1.drawImage(imagen29,320,240,null);
		        g1.drawImage(imagen30,320,320,null);
		       
		        File outputfilec2 = new File("combinada2.png");
		        ImageIO.write(combinada2,"PNG",outputfilec2);
		        endTime = System.nanoTime() - startTime;
				tiempoU = (double) endTime / 1000000000;
				System.out.println("tiempo de unión: "+tiempoU+ " segundos");
				tiempoT = tiempo + tiempoU;
				System.out.println("tiempo total: "+tiempoT+" segundos");

				
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
