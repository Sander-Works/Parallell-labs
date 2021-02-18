package no.hiof.itf23019.one_dimension_averaging;

import java.util.concurrent.Phaser;

/**
 * Wrapper class for implementing one-dimensional iterative averaging using
 * phasers.
 */
public final class OneDimAveragingPhaser {
    /**
     * Default constructor.
     */
    private OneDimAveragingPhaser() {
    }

    /**
     * Sequential implementation of one-dimensional iterative averaging.
     *
     * @param iterations The number of iterations to run
     * @param myNew A double array that starts as the output array
     * @param myVal A double array that contains the initial input to the
     *        iterative averaging problem
     * @param n The size of this problem
     */
    public static void runSequential(final int iterations, final double[] myNew,
            final double[] myVal, final int n) {
        double[] next = myNew;
        double[] curr = myVal;

        for (int iter = 0; iter < iterations; iter++) {
            for (int j = 1; j <= n; j++) {
                next[j] = (curr[j - 1] + curr[j + 1]) / 2.0;
            }
            double[] tmp = curr;
            curr = next;
            next = tmp;
        }
    }

    /**
     * An example parallel implementation of one-dimensional iterative averaging
     * that uses phasers as a simple barrier (arriveAndAwaitAdvance).
     *
     * @param iterations The number of iterations to run
     * @param myNew A double array that starts as the output array
     * @param myVal A double array that contains the initial input to the
     *        iterative averaging problem
     * @param n The size of this problem
     * @param tasks The number of threads/tasks to use to compute the solution
     */
    public static void runParallelBarrier(final int iterations,
            final double[] myNew, final double[] myVal, final int n,
            final int tasks) {
    	
    	//Create new Phaser and register parties
    	 Phaser ph = new Phaser(0);
         ph.bulkRegister(tasks);

    	
        Thread[] threads = new Thread[tasks];

        for (int ii = 0; ii < tasks; ii++) {
        	//use this i value to compute the chuncSize for each thread
        	final int i = ii;

            threads[ii] = new Thread(() -> {
            	
            	//Arrays for computing intermediate values
                double[] threadPrivateMyVal = myVal;
                double[] threadPrivateMyNew = myNew;

                
                //Computing the chunk for each thread
                final int chunkSize = (n + tasks - 1) / tasks;
                final int left = (i * chunkSize) + 1;
                int right = (left + chunkSize) - 1;
                if (right > n) right = n;

                for (int iter = 0; iter < iterations; iter++) {
                	//TODO: Computing the intermediate values of the chunk [left, right]
                    for (int j = left; j <= right; j++) {
                        threadPrivateMyNew[j] = (threadPrivateMyVal[j - 1] + threadPrivateMyVal[j + 1]) / 2.0;
                    }

                    //TODO: Set barrier arriveAndAwaitAdvance
                    ph.arriveAndAwaitAdvance();
                    //Swap the arrays
                    double[] temp = threadPrivateMyNew;
                    threadPrivateMyNew = threadPrivateMyVal;
                    threadPrivateMyVal = temp;
                }
            });
            threads[ii].start();
        }

        for (int ii = 0; ii < tasks; ii++) {
            try {
                threads[ii].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A parallel implementation of one-dimensional iterative averaging that
     * uses the Phaser.arrive and Phaser.awaitAdvance APIs to overlap
     * computation with barrier completion.
     *
     * TODO Complete this method based on the provided runSequential and
     * runParallelBarrier methods.
     *
     * @param iterations The number of iterations to run
     * @param myNew A double array that starts as the output array
     * @param myVal A double array that contains the initial input to the
     *              iterative averaging problem
     * @param n The size of this problem
     * @param tasks The number of threads/tasks to use to compute the solution
     */
    public static void runParallelFuzzyBarrier(final int iterations,
            final double[] myNew, final double[] myVal, final int n,
            final int tasks) {

    	//Create new Phaser and register parties
        Phaser ph = new Phaser(0);
        ph.bulkRegister(tasks);

        Thread[] threads = new Thread[tasks];

        for (int ii = 0; ii < tasks; ii++) {
            final int i = ii;

            threads[ii] = new Thread(() -> {
            	
            	//Arrays for computing intermediate values
                double[] threadPrivateMyVal = myVal;
                double[] threadPrivateMyNew = myNew;

                //Computing the chunk for each thread
                final int chunkSize = (n + tasks - 1) / tasks;
                final int left = (i * chunkSize) + 1;
                int right = (left + chunkSize) - 1;
                if (right > n) right = n;

                
                for (int iter = 0; iter < iterations; iter++) {
                	
                	//TODO: Computing the intermediate the left and right values
                    threadPrivateMyNew[left] = (threadPrivateMyVal[left - 1] + threadPrivateMyVal[left + 1]) / 2.0;

                    threadPrivateMyNew[right] = (threadPrivateMyVal[right - 1] + threadPrivateMyVal[right + 1]) / 2.0;

                	
                	//TODO: barrier arrive
                    int currentPhase = ph.arrive();
                	
                	//TODO: Computing the intermediate values between left and right values [left+1, right-1]
                    for (int j = left + 1; j <= right - 1; j++) {
                        threadPrivateMyNew[j] = (threadPrivateMyVal[j - 1] + threadPrivateMyVal[j + 1]) / 2.0;
                    }
                    
                    //TODO: barrier awaitAdvance
                    ph.awaitAdvance(currentPhase);

                    
                    //Swap the arrays
                    double[] temp = threadPrivateMyNew;
                    threadPrivateMyNew = threadPrivateMyVal;
                    threadPrivateMyVal = temp;
                }
            });
            threads[ii].start();
        }

        for (int ii = 0; ii < tasks; ii++) {
            try {
                threads[ii].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
