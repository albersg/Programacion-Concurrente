package ejercicio2;

import java.util.ArrayList;

public class EJ2 {
	private static final int M = 5;
	private static final int N = 100;

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		// Locks
		
		//LockRompeEmpate lock = new LockRompeEmpate(2*M);
		//LockTicket lock = new LockTicket(2*M);
		LockBakery lock = new LockBakery(2*M);
		
		Entero ent = new Entero();
		
		for(int i = 0; i < 2*M; i += 2) {
			threads.add(new Decrementator(N, i, ent, lock));
			threads.add(new Incrementator(N, i+1, ent, lock));
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
		
		System.out.println("Todas las hebras han terminado.");
		System.out.println("El resultado final de la variable es: " + ent.getN());
	}

}
