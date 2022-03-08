package ejercicio1;

public class Thread_ej1 extends Thread{

	private int id;
	private long time;

	public Thread_ej1(int id, long time) {
		this.id = id;
		this.time = time;
	}

	public void run() {
		System.out.println("Soy la hebra " + id);

		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Soy la hebra " + id);
	}

}
