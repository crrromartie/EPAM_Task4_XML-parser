package by.gaponenko.xmlparser.builder;

import by.gaponenko.xmlparser.entity.Vehicle;
import by.gaponenko.xmlparser.exception.ProjectException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVehicleBuilder {
    protected Set<Vehicle> vehicleSet;

    public AbstractVehicleBuilder() {
        vehicleSet = new HashSet<>();
    }

    public AbstractVehicleBuilder(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    public abstract void buildVehicles(String filePath) throws ProjectException;
}
