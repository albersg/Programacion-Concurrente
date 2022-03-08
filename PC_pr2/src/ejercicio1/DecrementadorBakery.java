package ejercicio1;

public class DecrementadorBakery extends Thread{
	int n;
	Entero ent;
	LockBakery2 loc;

	public DecrementadorBakery(int n, Entero ent, LockBakery2 loc) {
		this.n = n;
		this.loc = loc;
		this.ent = ent;
	}
	
	public void run() {
		while(n > 0) {
			loc.setTurn1(loc.getTurn2() + 1);
			
			while(loc.getTurn2() != 0 && loc.getTurn1() > loc.getTurn2());
						
			ent.dec();
			
			loc.setTurn1(0);
			n--;
		}
	}
}
