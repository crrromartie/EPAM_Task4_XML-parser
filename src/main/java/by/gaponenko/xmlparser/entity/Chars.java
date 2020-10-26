package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public class Chars {
    private String fuelType;
    private int power;
    private double fuelConsumption;
    private String transmission;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chars chars = (Chars) o;
        if (power != chars.power) {
            return false;
        }
        if (Double.compare(chars.fuelConsumption, fuelConsumption) != 0) {
            return false;
        }
        if (fuelType != null ? !fuelType.equals(chars.fuelType) : chars.fuelType != null) {
            return false;
        }
        return transmission != null ? transmission.equals(chars.transmission) : chars.transmission == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = fuelType != null ? fuelType.hashCode() : 0;
        result = 31 * result + power;
        temp = Double.doubleToLongBits(fuelConsumption);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Chars.class.getSimpleName() + "[", "]")
                .add("fuelTypes=" + fuelType)
                .add("power=" + power)
                .add("fuelConsumption=" + fuelConsumption)
                .add("transmission=" + transmission)
                .toString();
    }
}
