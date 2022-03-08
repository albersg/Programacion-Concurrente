package ejercicio1;

public class Decrementator extends Thread{
	int n;
	Entero ent;
	Lock loc;

	public Decrementator(int n, Entero ent, Lock loc) {
		this.n = n;
		this.loc = loc;
		this.ent = ent;
	}
	
	public void run() {
		while(n > 0) {
			loc.setIn1(true);
			loc.setLast(1);
			
			while(loc.isIn2() && loc.getLast() == 1);
			
			ent.dec();
			
			loc.setIn1(false);
			n--;
		}
	}
}
