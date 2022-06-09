package ejercicio2;

import java.util.concurrent.Semaphore;

public class Buffer implements Almacen {
	Semaphore empty;
	Semaphore full;
	Producto prod;
	
	public Buffer(Semaphore empty, Semaphore full) {
		this.empty = empty;
		this.full = full;
		this.prod = null;
	}

	@Override
	public void almacenar(Producto producto) throws InterruptedException {
		empty.acquire();
		
		prod = producto;
		System.out.println("Productor almacena el producto " + prod.get_num());
		
		full.release();
	}

	@Override
	public Producto extraer() throws InterruptedException {
		full.acquire();
		
		Producto aux = prod;
		System.out.println("Consumidor consume el producto " + aux.get_num());
		prod = null;
		
		empty.release();
		
		return aux;
	}

}
