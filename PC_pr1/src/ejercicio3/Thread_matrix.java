package ejercicio3;

public class Thread_matrix extends Thread{
	
	private int index;
	private int[] my_row;
	private int[][] matrix;
	private int[][] result;
	
	public Thread_matrix(int index, int[] my_row, int[][] matrix, int[][] result) {
		this.index = index;
		this.my_row = my_row;
		this.matrix = matrix;
		this.result = result;
	}
	
	public void run() {
		int n = my_row.length;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				result[index][i] += my_row[j]*matrix[j][i];
			}
		}
	}
}
