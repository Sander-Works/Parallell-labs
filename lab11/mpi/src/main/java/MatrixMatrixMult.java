import java.util.Random;

import mpi.*;

public class MatrixMatrixMult {
	private static boolean DEBUG = false;

	public static void main(String args[]) throws Exception {
		int me, size, n, i, j, k, count, remainder, myRows, rows;
		double[] matrix_A = null, matrix_B = null, matrix_C = null, local_matrix_A = null, local_matrix_C = null;
		int[] sendcounts, senddispls, recvcounts, recvdispls;
		
		double start = 0, end = 0;

		args = MPI.Init(args);
		me = MPI.COMM_WORLD.Rank();
		size = MPI.COMM_WORLD.Size();

		if (args.length == 0) 
		{
			if (me == 0)
				System.out.println("Error!. Sintaxis: MatrixMatrixMult N (NxN matrix)");

			MPI.Finalize();
			System.exit(0);
		}

		n = Integer.parseInt(args[0]);
		matrix_B = new double[n * n];
		sendcounts = new int[size];
		senddispls = new int[size];
		recvcounts = new int[size];
		recvdispls = new int[size];

		if (me == 0) 
		{
			System.out.println("Matrix-Matrix multiplication");
			System.out.println("Square matrix size: " + n + "x" + n);
			System.out.println("Number of processes: " + size);
			matrix_A = new double[n * n];
			matrix_C = new double[n * n];

			/* Initialize Matrix */
			Random random = new Random(20210404);
			
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					matrix_A[i * n + j] = random.nextInt(10);
					matrix_B[i * n + j] = random.nextInt(10);
				}
			}

			//Printing out the matrices
			if (DEBUG) 
			{
				System.out.println("Matrix A is:");
				for (i = 0; i < n; i++) 
				{
					for (j = 0; j < n; j++)
						System.out.print(matrix_A[i * n + j] + " ");

					System.out.println();
				}
				System.out.println("Matrix B is:");
				for (i = 0; i < n; i++) 
				{
					for (j = 0; j < n; j++)
						System.out.print(matrix_B[i * n + j] + " ");

					System.out.println();
				}

			}
		}
		
		if (me == 0) 
		{
			start = MPI.Wtime();
		}

		count = n / size;
		remainder = n % size;
		//Number of rows to be computed by each process
		myRows = me < remainder ? count + 1 : count; 
		local_matrix_C = new double[myRows * n];
		local_matrix_A = new double[myRows * n];

		for (i = 0; i < size; ++i) 
		{
			//Computing number of rows to be computed by process i
			rows = (i < remainder) ? count + 1 : count;
			//Total elements to be sent and received by process i (each row has n elements)
			recvcounts[i] = rows * n;
			sendcounts[i] = rows * n;
			
			//Computing starting index in the buffer for each process. 
			//Starting index of process i = starting index of process [i - 1] 
			//plus total numbers of elements to be computed by process [i-1]
			if (i > 0) {
				recvdispls[i] = recvdispls[i - 1] + recvcounts[i - 1];
				senddispls[i] = senddispls[i - 1] + sendcounts[i - 1];
			} else {
				recvdispls[i] = 0;
				senddispls[i] = 0;
			}
			if (DEBUG && me == 0)
				System.out.println("rank " + i + ": rows " + rows + " sendcounts " + sendcounts[i] + " senddispls "
						+ senddispls[i] + " recvcounts " + recvcounts[i] + " recvdispls " + recvdispls[i]);
		}

		MPI.COMM_WORLD.Barrier();

		
		
		
		//Process 0 Broadcast matrix B to the other processes
		MPI.COMM_WORLD.Bcast(matrix_B, 0, n * n, MPI.DOUBLE, 0);
		
		//Process 0 scatters matrix A to other processes (each process keeps a local copy of the assigned rows)
		MPI.COMM_WORLD.Scatterv(matrix_A, 0, sendcounts, senddispls, MPI.DOUBLE, local_matrix_A, 0, myRows * n,
				MPI.DOUBLE, 0);
		

		

		/* Computation */
		for (i = 0; i < myRows; i++) {
			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++)
					local_matrix_C[i * n + j] += local_matrix_A[i * n + k] * matrix_B[k * n + j];
			}
		}
	

		//Each process sends the computed rows of matrix C to root process 0
		MPI.COMM_WORLD.Gatherv(local_matrix_C, 0, myRows * n, MPI.DOUBLE, matrix_C, 0, recvcounts, recvdispls,
				MPI.DOUBLE, 0);
		

		if (me == 0) {
			
			end = MPI.Wtime();
			
			if (DEBUG) {
				System.out.println("Matrix result is:");

				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++)
						System.out.print(matrix_C[i * n + j] + " ");

					System.out.println();
				}
			}
			
			double total = end - start;
			System.out.format("Running Time = %f", total);
		}
		MPI.Finalize();
	}
}
