package ejercicio1;

public class EJ1 {
	public static final int N = 10000;

	public static void main(String[] args) {
		Entero ent = new Entero();
		LockRompeEmpate2 loc = new LockRompeEmpate2();
		
		DecrementatorTie th1 = new DecrementatorTie(N, ent, loc);
		IncrementatorTie th2 = new IncrementatorTie(N, ent, loc);
		
		/*LockBakery2 loc = new LockBakery2();
		
		DecrementadorBakery th1 = new DecrementadorBakery(N, ent, loc);
		IncrementadorBakery th2 = new IncrementadorBakery(N, ent, loc);*/
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Todas las hebras han terminado.");
		System.out.println("El resultado final de la variable es: " + ent.getN());

	}

}
