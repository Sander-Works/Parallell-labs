package no.hiof.itf23019.one_dimension_averaging;

public class Main {
	
	final static private int niterations = 10000;
	final static private int N = 2 * 1024 * 1024;

	public static void main(String[] args) {
		
		int ncores =  Runtime.getRuntime().availableProcessors();
		int RUNS = 5;

		double[] myNewSeq = Utils.createArray(N, niterations);
		double[] myValSeq = Utils.createArray(N, niterations);
		
		double[] myNewBarrier = Utils.createArray(N, niterations);
		double[] myValBarrier = Utils.createArray(N, niterations);
		
		double[] myNewFuzzy = Utils.createArray(N, niterations);
		double[] myValFuzzy = Utils.createArray(N, niterations);
		
		 long sequentialTotalTime = 0;
		 long barrierTotalTime = 0;
	     long fuzzyTotalTime = 0;
	     
	     System.out.println("ncores=" + ncores);
		
		 for (int r = 0; r < RUNS; r++) {
			 
			 	System.out.println("Run #" + r);
			 	
			 	System.out.println("Running Sequential ... ");
	            final long seqentialStartTime = System.currentTimeMillis();
	            OneDimAveragingPhaser.runSequential(niterations, myNewSeq, myValSeq, N);
	            final long sequentialEndTime = System.currentTimeMillis();

	            System.out.println("Running Barrier  ... ");
	            final long barrierStartTime = System.currentTimeMillis();
	            OneDimAveragingPhaser.runParallelBarrier(niterations, myNewBarrier, myValBarrier, N, ncores);
	            final long barrierEndTime = System.currentTimeMillis();
	            
	            if (niterations % 2 == 0) {
	                Utils.checkResult(myNewSeq, myNewBarrier);
	            } else {
	                Utils.checkResult(myValSeq, myValBarrier);
	            }
	            
	            System.out.println("Running Fuzzy ... ");
	            final long fuzzyStartTime = System.currentTimeMillis();
	            OneDimAveragingPhaser.runParallelFuzzyBarrier(niterations, myNewFuzzy, myValFuzzy, N, ncores);
	            final long fuzzyEndTime = System.currentTimeMillis();

	            if (niterations % 2 == 0) {
	                Utils.checkResult(myNewSeq, myNewFuzzy);
	            } else {
	                Utils.checkResult(myValSeq, myValFuzzy);
	            }
	            

	            sequentialTotalTime += (sequentialEndTime - seqentialStartTime);
	            barrierTotalTime += (barrierEndTime - barrierStartTime);
	            fuzzyTotalTime += (fuzzyEndTime - fuzzyStartTime);
	        }

		System.out.println("Sequential Run Time: " + sequentialTotalTime/RUNS);
		System.out.println("Barrier Run Time: " + barrierTotalTime/RUNS);
		System.out.println("Fuzzy Run Time: " + fuzzyTotalTime/RUNS);
		
		//TODO: Compute the speed up
		System.out.println("Barrier speedup: " + sequentialTotalTime/barrierTotalTime);
		System.out.println("Fuzzy speedup: " + sequentialTotalTime/fuzzyTotalTime);

	}
}
