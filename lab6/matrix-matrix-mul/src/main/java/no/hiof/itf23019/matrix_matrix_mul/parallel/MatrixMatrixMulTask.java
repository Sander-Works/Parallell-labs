package no.hiof.itf23019.matrix_matrix_mul.parallel;
import java.util.concurrent.RecursiveAction;


public class MatrixMatrixMulTask extends RecursiveAction{

	private static final long serialVersionUID = 935729182687362668L;

	private int[][] matrix1;
	private int[][] matrix2;
	private int startRow, endRow;
	private long[][] result;
	private int threshold = 1_000;
	
	public MatrixMatrixMulTask(int[][] matrix1, int[][] matrix2, int startRow, int endRow, long[][] result) {
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.startRow = startRow;
		this.endRow = endRow;
		this.result = result;
	}

	@Override
	protected void compute() {
		//TODO: implement the fork/join method
		if (endRow - startRow <= threshold) {
			for (int i = startRow; i < endRow; i++)
				for (int j = 0; j < matrix2[0].length; j++)
					for (int k = 0; k < matrix2.length; k++)
						result[i][j] += matrix1[i][k] * matrix2[k][j];

		} else {
			int midelRow = (startRow + endRow) / 2;
			MatrixMatrixMulTask left = new MatrixMatrixMulTask(matrix1, matrix2, startRow, midelRow, result); //This is to get to get the first half of the matrix's
			MatrixMatrixMulTask right = new MatrixMatrixMulTask(matrix1, matrix2, midelRow, endRow, result); //This is to get the second half of the matrix's

			invokeAll(left,right);
		}
	}
}

