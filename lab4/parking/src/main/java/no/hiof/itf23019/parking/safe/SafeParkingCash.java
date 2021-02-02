package no.hiof.itf23019.parking.safe;

public class SafeParkingCash {

	private static final int cost=1;
	
	private long cash;
	
	public SafeParkingCash() {
		cash=0;
	}
	
	public synchronized void vehiclePay() {
		cash+=cost;
	}
	
	public void close() {
		System.out.printf("Closing accounting%n");
		long totalAmmount;
		synchronized (this) {
			totalAmmount=cash;
			cash=0;
		}
		System.out.printf("The total ammount is : %d",totalAmmount);
	}
}
