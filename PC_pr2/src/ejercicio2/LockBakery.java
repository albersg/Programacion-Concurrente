package ejercicio2;

import java.util.Arrays;

public class LockBakery implements Lock{
	private int num_threads;
	
	private volatile int turn[];
	
	public LockBakery(int num_threads) {
		this.num_threads = num_threads;
		
		turn = new int[num_threads];
		
		for(int i = 0; i < num_threads;i++) {
			turn[i] = 0;
		}
	}

	private boolean compare(int a, int b, int c, int d) {
		return a > c || (a == c && b > d);
	}
	
	@Override
	public void takeLock(int id) {
		turn[id] = 1;
		turn = turn;
		
		turn[id] = Arrays.stream(turn).max().getAsInt() + 1;
		turn = turn;
			
		for(int j = 0; j < num_threads;j++) {
			if(id != j) {
				while(turn[j] != 0 && compare(turn[id], id, turn[j], j));
			}
		}
	}

	@Override
	public void releaseLock(int id) {
		turn[id] = 0;
		turn = turn;
	}

}
