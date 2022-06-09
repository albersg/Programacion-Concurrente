package ejercicio3;

public class Consumidor extends Thread{

	Buffer buff;
	int N;
	
	public Consumidor(Buffer buff, int N) {
		this.buff = buff;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i < N;i++) {
			try {
				buff.extraer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
