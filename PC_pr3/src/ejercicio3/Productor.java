package ejercicio3;

public class Productor extends Thread{

	Buffer buff;
	int id;
	int N;
	
	public Productor(Buffer buff, int id, int N) {
		this.buff = buff;
		this.id = id;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i < N;i++) {
			try {
				buff.almacenar(new Producto(id*N + i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
