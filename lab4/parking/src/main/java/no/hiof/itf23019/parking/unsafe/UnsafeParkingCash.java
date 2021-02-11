package no.hiof.itf23019.parking.unsafe;

public class UnsafeParkingCash {

	private static final int cost=1;
	
	private long cash;
	
	public UnsafeParkingCash() {
		cash=0;
	}
	
	public void vehiclePay() {
		cash+=cost;
	}
	
	public void close() {
		System.out.printf("Closing accounting%n");
		long totalAmmount;
		totalAmmount=cash;
		cash=0;
		System.out.printf("The total ammount is : %d",totalAmmount);
	}
}
