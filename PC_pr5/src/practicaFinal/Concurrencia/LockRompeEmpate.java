package practicaFinal.Concurrencia;

public class LockRompeEmpate{
	private volatile int last;
	private volatile boolean in1;
	private volatile boolean in2;
	
	public LockRompeEmpate() {
		last = 0;
		in1 = false;
		in2 = false;
	}
	
	public void requestLockP1() {
		in1 = true;
		last = 1;
		
		while(in2 && last == 1);
	}
	
	public void releaseLockP1() {
		in1 = false;
	}
	
	public void requestLockP2() {
		in2 = true;
		last = 2;
	 
		while(in1 && last == 2);
	}
	
	public void releaseLockP2() {
		in2 = false;
	}
	
}
