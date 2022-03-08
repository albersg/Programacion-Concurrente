package ejercicio1;

public class LockRompeEmpate2{
	private volatile int last;
	private volatile boolean in1;
	private volatile boolean in2;
	
	public LockRompeEmpate2() {
		last = 0;
		in1 = false;
		in2 = false;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public boolean isIn1() {
		return in1;
	}

	public void setIn1(boolean in1) {
		this.in1 = in1;
	}

	public boolean isIn2() {
		return in2;
	}

	public void setIn2(boolean in2) {
		this.in2 = in2;
	}
	
	
}
