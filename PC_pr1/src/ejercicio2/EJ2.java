package ejercicio2;

import java.util.ArrayList;

public class EJ2 {

	private static final int N = 1000;
	private static final int M = 500;

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Entero ent = new Entero();
		
		for(int i = 0; i < 2*M; i += 2) {
			threads.add(new Thread_decrement(ent, N));
			threads.add(new Thread_increment(ent, N));
		}

		for(int i = 0; i < 2*M; i++) {
			threads.get(i).start();
		}
		
		try {
			for(int i = 0; i < 2*M; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("El resultado final de la variable es: " + ent.getValue());
	}

}
