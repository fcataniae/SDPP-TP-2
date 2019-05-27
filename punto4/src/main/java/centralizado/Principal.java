package centralizado;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		SobelMain centralizado = new SobelMain("imagen.png",400);
		long endTime = System.nanoTime() - startTime;
		double tiempo = (double) endTime / 1000000000;

		System.out.println("tiempo de ejecución: "+tiempo+ " segundos");
	}

}
