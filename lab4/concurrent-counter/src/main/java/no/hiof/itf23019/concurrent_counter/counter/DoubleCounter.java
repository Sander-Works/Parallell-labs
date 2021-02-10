package no.hiof.itf23019.concurrent_counter.counter;

public class DoubleCounter {

	private int counter1, counter2;

	// To control the concurrent access to the two counters
	private final Object counter1Control, counter2Control;

	public DoubleCounter() {
		counter1 = 0;
		counter2 = 0;

		// TODO: Initialize the two control object
		counter1Control = new Object();
		counter2Control = new Object();
	}

	
	//TODO: implement the monitor 
	public void incrementCounter1() {

		counter1++;
	}

	//TODO: implement the monitor 
	public void decrementCounter1() {
		counter1--;
	}

	//TODO: implement the monitor 
	public int getCounter1() {
		return counter1;
	}

	//TODO: implement the monitor 
	public void incrementCounter2() {

		counter2++;
	}

	//TODO: implement the monitor 
	public void decrementCounter2() {
		counter2--;
	}

	//TODO: implement the monitor 
	public int getCounter2() {
		return counter2;
	}

	@Override
	public String toString() {
		return "DoubleCounter [counter1=" + counter1 + ", counter2=" + counter2 + "]";
	}

}
