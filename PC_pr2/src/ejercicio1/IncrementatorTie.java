package ejercicio1;

public class IncrementatorTie extends Thread {
	int n;
	Entero ent;
	LockRompeEmpate2 loc;
	
	public IncrementatorTie(int n, Entero ent, LockRompeEmpate2 loc) {
		this.n = n;
		this.loc = loc;
		this.ent = ent;
	}
	
	public void run() {
		while(n > 0) {
			loc.setIn2(true);
			loc.setLast(2);
			
			while(loc.isIn1() && loc.getLast() == 2);
			
			ent.inc();
			
			loc.setIn2(false);
			n--;
		}
	}
}
