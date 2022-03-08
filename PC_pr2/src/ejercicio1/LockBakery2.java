package ejercicio1;

public class LockBakery2 {
	private volatile int turn1;
	private volatile int turn2;
	
	public LockBakery2() {
		turn1 = 1;
		turn2 = 1;
	}

	public int getTurn1() {
		return turn1;
	}

	public void setTurn1(int turn1) {
		this.turn1 = turn1;
	}

	public int getTurn2() {
		return turn2;
	}

	public void setTurn2(int turn2) {
		this.turn2 = turn2;
	}
	
}
