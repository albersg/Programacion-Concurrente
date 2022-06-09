package ejercicio2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class EJ2 {
	private static final int NUM_PRODUCTORES = 10;
	private static final int NUM_CONSUMIDORES = 10;
	
	private static final int ITERACIONES_PROD = 2;
	private static final int ITERACIONES_CONS = 2;

	public static void main(String[] args) {
		// Declaration of the threads
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		// Declaration of the semaphores
		// We need initialize 'empty' to one because we need that one process enter
		Semaphore empty = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		
		// Declaration of the class that represents the buffer
		Buffer buff = new Buffer(empty, full);
		
		// Creation of all producers
		for(int i = 0; i < NUM_PRODUCTORES; i++) {
			threads.add(new Productor(buff, i, ITERACIONES_PROD));
		}
		
		// Creation of all consumers
		for(int i = 0; i < NUM_CONSUMIDORES; i++) {
			threads.add(new Consumidor(buff, ITERACIONES_CONS));
		}

		// Start all the threads
		for(int i = 0; i < NUM_PRODUCTORES + NUM_CONSUMIDORES; i++) {
			threads.get(i).start();
		}
		
		// Do the joins
		try {
			for(int i = 0; i < NUM_PRODUCTORES + NUM_CONSUMIDORES; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// The program ends
		System.out.println();
		System.out.println("Todas las hebras han terminado.");
	}

}
