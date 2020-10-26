package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public class Bus extends Vehicle {
    private int passengerCapacity;

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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
        Bus bus = (Bus) o;
        return passengerCapacity == bus.passengerCapacity;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + passengerCapacity;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bus.class.getSimpleName() + "[", "]")
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
                .add("passenger_capacity=" + passengerCapacity)
                .add(getVehicleId())
                .add("description:" + " " + getDescription())
                .toString();
    }
}
