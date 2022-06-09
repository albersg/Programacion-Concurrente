package ejercicio1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class EJ1 {
	private static final int M = 10;
	private static final int N = 100;

	public static void main(String[] args) {
		// Declaration of the threads
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		// Declaration of the semaphore
		// We need initialize it to one because we need that one process enter
		Semaphore sem = new Semaphore(1);
		
		// Declaration of the class that contains the variable that we are going to decrement
		Entero ent = new Entero();
		
		// Creation of all threads, the decrementator and incrementators
		for(int i = 0; i < 2*M; i += 2) {
			threads.add(new Decrementator(N, i, ent, sem));
			threads.add(new Incrementator(N, i+1, ent, sem));
		}

		// Start all the threads
		for(int i = 0; i < 2*M; i++) {
			threads.get(i).start();
		}
		
		// Do the join
		try {
			for(int i = 0; i < 2*M; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Print the result of the variable
		System.out.println("Todas las hebras han terminado.");
		System.out.println("El resultado final de la variable es: " + ent.getN());
	}

}
