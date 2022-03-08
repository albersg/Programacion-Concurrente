package ejercicio1;

public class DecrementatorTie extends Thread{
	int n;
	Entero ent;
	LockRompeEmpate2 loc;

	public DecrementatorTie(int n, Entero ent, LockRompeEmpate2 loc) {
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
