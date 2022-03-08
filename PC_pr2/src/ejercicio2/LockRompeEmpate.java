package ejercicio2;

public class LockRompeEmpate implements Lock{
	private int num_threads;
	
	private volatile int[] in;
	private volatile int[] last;
	
	public LockRompeEmpate(int num_threads) {
		this.num_threads = num_threads;
		
		in = new int[num_threads];
		last = new int[num_threads];
	
		for(int i = 0; i < num_threads; i++) {
			in[i] = -1;
			last[i] = -1;
		}
	}

	@Override
	public void takeLock(int id) {
		for(int j = 0; j < num_threads; j++) {
			in[id] = j;
			in = in;
			
			last[j] = id;
			last = last;
			
			for(int k = 0; k < num_threads; k++) {
				if(k != id)
					while((in[k] >= in[id]) && (last[j] == id));
			}
		}
	}
	
	@Override
	public void releaseLock(int id) {
		in[id] = -1;
		in = in;
	}
	
}
