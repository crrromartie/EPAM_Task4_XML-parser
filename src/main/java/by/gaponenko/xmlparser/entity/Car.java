package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public class Car extends Vehicle {
    private boolean awd;
    private double acceleration;
    private int maxSpeed;

    public boolean isAwd() {
        return awd;
    }

    public void setAwd(boolean awd) {
        this.awd = awd;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public int getTransmission() {
        return maxSpeed;
    }

    public void setTransmission(int transmission) {
        this.maxSpeed = transmission;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (awd != car.awd) return false;
        if (Double.compare(car.acceleration, acceleration) != 0) return false;
        return maxSpeed == car.maxSpeed;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (awd ? 1 : 0);
        temp = Double.doubleToLongBits(acceleration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + maxSpeed;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
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
                .add("awd=" + awd)
                .add("acceleration=" + acceleration)
                .add("max_speed=" + maxSpeed)
                .add(getVehicleId())
                .add("description:" + " " + getDescription())
                .toString();
    }
}
