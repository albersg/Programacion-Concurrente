package ejercicio3;

import java.util.concurrent.Semaphore;

public class Buffer implements Almacen {
	int N;
	int ini, fin;
	Semaphore empty, full;
	Semaphore mutexP, mutexC;
	Producto[] prod;
	
	public Buffer(Semaphore empty, Semaphore full, int N) {
		this.empty = empty;
		this.full = full;
		this.prod = new Producto[N];
		this.N = N;
		
		ini = 0;
		fin = 0;
		
		mutexP = new Semaphore(1);
		mutexC = new Semaphore(1);
	}

	@Override
	public void almacenar(Producto producto) throws InterruptedException {
		empty.acquire();
		mutexP.acquire();
		
		prod[fin] = producto;
		System.out.println("Productor almacena el producto " + prod[fin].get_num());
		fin = (fin + 1) % N;
		
		mutexP.release();
		full.release();
	}

	@Override
	public Producto extraer() throws InterruptedException {
		full.acquire();
		mutexC.acquire();
		
		Producto aux = prod[ini];
		prod[ini] = null;
		ini = (ini + 1) % N;
		System.out.println("Consumidor consume el producto " + aux.get_num());
		
		mutexC.release();
		empty.release();
		
		return aux;
	}

}
