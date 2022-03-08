package ejercicio2;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket implements Lock{	
	private volatile int[] turn;
	private volatile AtomicInteger number;
	private volatile int next;
	
	public LockTicket(int num_threads) {		
		turn = new int[num_threads];
		
		for(int i = 0; i < num_threads; i++)
			turn[i] = -1;
		number = new AtomicInteger(0);
		next = 0;
	}

	@Override
	public void takeLock(int id) {
		turn[id] = number.getAndAdd(1);
		turn = turn;
		
		while(turn[id] != next);
	}

	@Override
	public void releaseLock(int id) {
		next++;
	}

}
