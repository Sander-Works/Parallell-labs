package no.hiof.itf23019.parking.unsafe;

public class UnsafeParkingStats {

	/**
	 * This two variables store the number of cars and motorcycles in the
	 * parking
	 */
	private long numberCars;
	private long numberMotorcycles;

	private UnsafeParkingCash cash;

	/**
	 * Constructor of the class
	 */
	public UnsafeParkingStats(UnsafeParkingCash cash) {
		numberCars = 0;
		numberMotorcycles = 0;
		this.cash = cash;
	}

	public void carComeIn() {
		numberCars++;
	}

	public void carGoOut() {
		numberCars--;
		cash.vehiclePay();
	}

	public void motoComeIn() {
		numberMotorcycles++;
	}

	public void motoGoOut() {
		numberMotorcycles--;
		cash.vehiclePay();
	}

	public long getNumberCars() {
		return numberCars;
	}

	public long getNumberMotorcycles() {
		return numberMotorcycles;
	}

}
