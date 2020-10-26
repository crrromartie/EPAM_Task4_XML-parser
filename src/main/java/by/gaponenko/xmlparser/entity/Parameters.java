package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public class Parameters {
    private double length;
    private double width;
    private int weight;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parameters parameters = (Parameters) o;
        if (Double.compare(parameters.length, length) != 0) {
            return false;
        }
        if (Double.compare(parameters.width, width) != 0) {
            return false;
        }
        return weight == parameters.weight;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Parameters.class.getSimpleName() + "[", "]")
                .add("length=" + length)
                .add("width=" + width)
                .add("weight=" + weight)
                .toString();
    }
}
