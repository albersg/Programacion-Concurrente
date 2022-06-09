package ejercicio1;

import java.util.concurrent.Semaphore;

public class Incrementator extends Thread {
	int id;
	Entero ent;
	Semaphore sem;
	int N;

	public Incrementator(int N, int id, Entero ent, Semaphore sem) {
		this.id = id;
		this.sem = sem;
		this.ent = ent;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i <N;i++) {
			try {
				sem.acquire();
				ent.inc();
				sem.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
