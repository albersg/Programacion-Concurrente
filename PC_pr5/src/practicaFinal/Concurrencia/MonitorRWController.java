package practicaFinal.Concurrencia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Implementación de Lectores/Escritores de clase hecha en Java.
 */

public class MonitorRWController {
	private final Lock l;
	private final Condition ok_to_read;
	private final Condition ok_to_write;
	private int nr;
	private int nw;

	public MonitorRWController() {
		l = new ReentrantLock(true);
		ok_to_read = l.newCondition();
		ok_to_write = l.newCondition();
	}

	// Devuelve el permiso para leer
	public void release_read() {
		l.lock();

		while (nw > 0) {
			try {
				ok_to_read.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		nr++;

		l.unlock();
	}

	// Devuelve el permiso para escribir
	public void release_write() {
		l.lock();

		nw--;
		ok_to_write.signal();
		ok_to_read.signalAll();

		l.unlock();

	}

	// Pide permiso para leer
	public void request_read() {
		l.lock();

		nr--;
		if (nr == 0)
			ok_to_write.signal();

		l.unlock();
	}

	// Pide permiso para escribir
	public void request_write() {
		l.lock();

		while (nr > 0 || nw > 0) {
			try {
				ok_to_write.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		nw++;

		l.unlock();
	}
}
