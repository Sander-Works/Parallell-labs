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

		MatrixMatrixMulTask left = new MatrixMatrixMulTask(matrix1, matrix2,  startRow, endRow, result); //This is to get to get the first half of the matrix's
		MatrixMatrixMulTask right = new MatrixMatrixMulTask(matrix1, matrix2, startRow, endRow, result); //This is to get the second half of the matrix's

		left.fork();
		right.compute();
		left.join();
	}
		}

			
	}


}
