package ejercicio2;

public class Decrementator extends Thread{
	int id;
	Entero ent;
	Lock loc;
	int N;

	public Decrementator(int N, int id, Entero ent, Lock loc) {
		this.id = id;
		this.loc = loc;
		this.ent = ent;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i <N;i++) {
			loc.takeLock(id);
			ent.dec();
			loc.releaseLock(id);
		}
	}
}
