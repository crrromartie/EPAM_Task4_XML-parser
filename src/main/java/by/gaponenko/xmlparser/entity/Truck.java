package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public class Truck extends Vehicle {
    private int cargoCapacity;

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Truck truck = (Truck) o;
        return cargoCapacity == truck.cargoCapacity;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cargoCapacity;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Truck.class.getSimpleName() + "[", "]")
                .add("brand=" + getBrand())
                .add("model=" + getModel())
                .add("origin=" + getOrigin())
                .add("price=" + getPrice())
                .add("fuel_type=" + getChars().getFuelType())
                .add("power=" + getChars().getPower())
                .add("fuel_consumption=" + getChars().getFuelConsumption())
                .add("transmission=" + getChars().getTransmission())
                .add("length=" + getParameters().getLength())
                .add("width=" + getParameters().getWidth())
                .add("weight=" + getParameters().getWeight())
                .add("cargo_capacity=" + cargoCapacity)
                .add(getVehicleId())
                .add("description:" + " " + getDescription())
                .toString();
    }
}
