package ejercicio1;

public class EJ1 {

	public static final long TIME = 5000;
	private static final int NUM_THREADS = 5;

	public static void main(String[] args) {
		Thread_ej1[] threads = new Thread_ej1[NUM_THREADS];

		for (int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new Thread_ej1(i, TIME);
			threads[i].start();
		}

		for (int i = 0; i < NUM_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Todas las hebras han terminado");
	}

}
