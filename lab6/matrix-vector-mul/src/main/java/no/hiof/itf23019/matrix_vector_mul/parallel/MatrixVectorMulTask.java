package no.hiof.itf23019.matrix_vector_mul.parallel;
import java.util.concurrent.RecursiveAction;


public class MatrixVectorMulTask extends RecursiveAction{

	private static final long serialVersionUID = 935729182687362668L;

	private int[][] matrix;
	private int[] vector;
	private int startIndex, endIndex;
	private long[] result;
	private int threshold = 1_000;
	
	public MatrixVectorMulTask(int[][] matrix, int[] vector, int startIndex, int endIndex, long[] result) {
		this.matrix = matrix;
		this.vector = vector;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.result = result;
	}
	@Override
	protected void compute() {
		//TODO: implement the fork/join method
			
	}
	
	

}
