package no.hiof.itf23019.parking.safe;

public class SafeParkingStats {
	
	/**
	 * This two variables store the number of cars and motorcycles in the parking
	 */
	private long numberCars;
	private long numberMotorcycles;

	/**
	 * Two objects for the synchronization. ControlCars synchronizes the
	 * access to the numberCars attribute and controlMotorcycles synchronizes
	 * the access to the numberMotorcycles attribute.
	 */
	private final Object controlCars, controlMotorcycles;
	
	
	private SafeParkingCash cash;
	/**
	 * Constructor of the class
	 */
	public SafeParkingStats (SafeParkingCash cash) {
		numberCars=0;
		numberMotorcycles=0;
		controlCars=new Object();
		controlMotorcycles=new Object();
		this.cash=cash;
	}
	
	
	public void carComeIn() {
		synchronized (controlCars) {
			numberCars++;
		}
	}
	
	public void carGoOut() {
		synchronized (controlCars) {
			numberCars--;
		}
		cash.vehiclePay();
	}
	
	public long getNumberCars() {
		synchronized (controlCars) {
			return numberCars;
		}
	}
	
	
	public void motoComeIn() {
		synchronized (controlMotorcycles) {
			numberMotorcycles++;
		}
	}
	
	public void motoGoOut() {
		synchronized (controlMotorcycles) {
			numberMotorcycles--;
		}
		cash.vehiclePay();
	}

	public long getNumberMotorcycles() {
		synchronized (controlMotorcycles) {
			return numberMotorcycles;
		}
	}
	
	

}
