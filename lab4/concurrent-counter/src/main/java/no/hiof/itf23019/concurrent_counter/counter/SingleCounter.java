package no.hiof.itf23019.concurrent_counter.counter;

public class SingleCounter {
	
	private int counter = 0;
	
	public SingleCounter() {
		counter = 0;
	}

	//TODO: implement the monitor 
    public  void increment() {
        
    	counter++;
    }

  //TODO: implement the monitor 
    public  void decrement() {
    	counter--;
    }

  //TODO: implement the monitor 
    public  int getCounter() {
        return counter;
    }

	@Override
	public String toString() {
		return "SingleCounter [counter=" + counter + "]";
	}
    
    
}
