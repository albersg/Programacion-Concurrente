package ejercicio1;

import java.util.concurrent.Semaphore;

public class Decrementator extends Thread{
	int id;
	Entero ent;
	Semaphore sem;
	int N;

	public Decrementator(int N, int id, Entero ent, Semaphore sem) {
		this.id = id;
		this.sem = sem;
		this.ent = ent;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i <N;i++) {
			try {
				sem.acquire();
				ent.dec();
				sem.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
