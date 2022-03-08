package ejercicio3;

public class EJ3 {

	private static final int N = 4;
	
	private static void printMatrix(int [][] result) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.printf("%4d", result[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Thread_matrix[] threads = new Thread_matrix[N];
		int matrix1[][] = new int[N][N];
		int matrix2[][] = new int[N][N];
		int result[][] = new int[N][N];

		// Initialize both matrix
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				matrix1[i][j] = i+j;
				matrix2[i][j] = i*j;
			}
		}
		
		System.out.println("Las matrices iniciales son:");
		printMatrix(matrix1);
		printMatrix(matrix2);

		for (int i = 0; i < N; i++) {
			threads[i] = new Thread_matrix(i, matrix1[i], matrix2, result);
			threads[i].start();
		}
		
		for (int i = 0; i < N; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("La matriz resultado es la siguiente:");
		printMatrix(result);

	}

}
