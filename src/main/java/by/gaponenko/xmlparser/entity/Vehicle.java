package by.gaponenko.xmlparser.entity;

import java.util.StringJoiner;

public abstract class Vehicle {
    private String vehicleId;
    private String description;
    private String brand;
    private String model;
    private String origin;
    private int price;
    private Chars chars;
    private Parameters parameters;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Chars getChars() {
        return chars;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        if (price != vehicle.price) {
            return false;
        }
        if (vehicleId != null ? !vehicleId.equals(vehicle.vehicleId) : vehicle.vehicleId != null) {
            return false;
        }
        if (description != null ? !description.equals(vehicle.description) : vehicle.description != null) {
            return false;
        }
        if (brand != null ? !brand.equals(vehicle.brand) : vehicle.brand != null) {
            return false;
        }
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) {
            return false;
        }
        if (origin != null ? !origin.equals(vehicle.origin) : vehicle.origin != null) {
            return false;
        }
        if (chars != null ? !chars.equals(vehicle.chars) : vehicle.chars != null) {
            return false;
        }
        return parameters != null ? parameters.equals(vehicle.parameters) : vehicle.parameters == null;
    }

    @Override
    public int hashCode() {
        int result = vehicleId != null ? vehicleId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vehicle.class.getSimpleName() + "[", "]")
                .add("vehicleId=" + vehicleId)
                .add("description=" + description)
                .add("brand=" + brand)
                .add("model=" + model)
                .add("origin=" + origin)
                .add("price=" + price)
                .add("chars=" + chars)
                .add("parameters=" + parameters)
                .toString();
    }
}
