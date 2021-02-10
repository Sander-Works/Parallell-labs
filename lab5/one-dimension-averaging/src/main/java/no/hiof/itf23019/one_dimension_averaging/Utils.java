package no.hiof.itf23019.one_dimension_averaging;

public class Utils {

    public static double[] createArray(final int N, final int iterations) {
        final double[] input = new double[N + 2];
        int index = N + 1;
        while (index > 0) {
            input[index] = 1.0;
            index -= (iterations / 4);
        }
        return input;
    }
	
	
    public static void checkResult(final double[] ref, final double[] output) {
        for (int i = 0; i < ref.length; i++) {
        	if(ref[i] != output[i])
        	{
        		System.out.println( "Mismatch on output at element " + i);
        		break;
        	}
        }
    }
}
