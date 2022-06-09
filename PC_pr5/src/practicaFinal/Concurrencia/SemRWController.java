package practicaFinal.Concurrencia;

import java.util.concurrent.Semaphore;

/*
 * Implementación hecha en clase del problema de lectores y escritores con semáforos.
 */

public class SemRWController {
	private int nr; // Número de lectores
	private int nw; // Número de escritores
	private int dr; // Número de lectores retrasados
	private int dw; // Número de escritores retrasados
	
	private Semaphore e; // Actúa como mutex
	private Semaphore r; // Usado para los lectores retrasados
	private Semaphore w; // Usado para los escritores retrasados
	
	public SemRWController() {
		nr = 0;
		nw = 0;
		dr = 0;
		dw = 0;
		
		e = new Semaphore(1);
		r = new Semaphore(0);
		w = new Semaphore(0);
	}
	
	
	// Devuelve el permiso para leer
	public void release_read() {
		try {
			e.acquire();
			nr--;
			
			// SIGNAL
			if ((nr == 0) && (dw > 0)) {
				dw--;
				w.release();
			}
			else {
				e.release();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	// Devuelve el permiso para escribir
	public void release_write() {
		try {
			e.acquire();
			nw--;
			
			// SIGNAL
			if (dr > 0) {
				dr--;
				r.release();
			}
			else if (dw > 0) {
				dw--;
				w.release();
			}
			else {
				e.release();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	// Pide permiso para leer
	public void request_read() {
		try {
			e.acquire();
			if (nw > 0) {
				dr++;
				e.release();
				r.acquire();
			}
			nr++;
			
			// SIGNAL
			if (dr > 0) {
				dr--;
				r.release();
			}
			else {
				e.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Pide permiso para escribir
	public void request_write() {
		try {
			e.acquire();
			if(nr > 0 || nw > 0) {
				dw++;
				e.release();
				w.acquire();
			}
			nw++;
			
			// SIGNAL
			e.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
