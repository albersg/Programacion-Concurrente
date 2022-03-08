package ejercicio2;

public class Thread_increment extends Thread {

	private Entero entero;
	private int num_op;

	public Thread_increment(Entero entero, int num_op) {
		this.entero = entero;
		this.num_op = num_op;
	}

	public void run() {
		for (int i = 0; i < num_op; i++)
			entero.increment();
	}
}
