package no.hiof.itf23019.car_api.model;

//Sample model class to demonstrate REST capabilities in the RestResource class
public class Car {

	private String brand;
	private String color;

	// JSON libraries will use the empty constructor to create a new object during
	// deserialization, so it is important to have one
	public Car() {
	}

	public Car(String brand, String color) {
		this.brand = brand;
		this.color = color;
	}

	// Getter methods are used during serialization
	public String getBrand() {
		return brand;
	}

	// After creating the object, JSON libraries use the setter methods during
	// deserialization
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + "]";
	}


	
}
