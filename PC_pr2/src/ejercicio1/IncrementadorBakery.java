package ejercicio1;

public class IncrementadorBakery extends Thread{
	int n;
	Entero ent;
	LockBakery2 loc;

	public IncrementadorBakery(int n, Entero ent, LockBakery2 loc) {
		this.n = n;
		this.loc = loc;
		this.ent = ent;
	}
	
	public void run() {
		while(n > 0) {
			loc.setTurn2(loc.getTurn1() + 1);
			
			while(loc.getTurn1() != 0 && loc.getTurn2() >= loc.getTurn1());
						
			ent.inc();
			
			loc.setTurn2(0);
			n--;
		}
	}
}
