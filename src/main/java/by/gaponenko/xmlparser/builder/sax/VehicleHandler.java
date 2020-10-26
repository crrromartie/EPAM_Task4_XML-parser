package by.gaponenko.xmlparser.builder.sax;

import by.gaponenko.xmlparser.builder.VehicleTag;
import by.gaponenko.xmlparser.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VehicleHandler extends DefaultHandler {
    static Logger logger = LogManager.getLogger();

    private Set<Vehicle> vehicleSet;
    private Vehicle currentVehicle;
    private VehicleTag currentVehicleTag;

    public VehicleHandler() {
        vehicleSet = new HashSet<>();
    }

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (VehicleTag.CAR.getTag().equals(qName)
                || VehicleTag.TRUCK.getTag().equals(qName)
                || VehicleTag.BUS.getTag().equals(qName)) {
            if (VehicleTag.CAR.getTag().equals(qName)) {
                currentVehicle = new Car();
            } else if (VehicleTag.TRUCK.getTag().equals(qName)) {
                currentVehicle = new Truck();
            } else {
                currentVehicle = new Bus();
            }
            currentVehicle.setVehicleId(attributes.getValue(VehicleTag.ID.getTag()));
            String description = attributes.getValue(VehicleTag.DESCRIPTION.getTag());
            if (description != null) {
                currentVehicle.setDescription(attributes.getValue(VehicleTag.DESCRIPTION.getTag()));
            } else {
                currentVehicle.setDescription("Description...");
            }
        } else {
            Optional<VehicleTag> optionalVehicleTag = VehicleTag.getVehicleTagByValue(qName);
            optionalVehicleTag.ifPresent(vehicleTag -> currentVehicleTag = vehicleTag);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (VehicleTag.CAR.getTag().equals(qName)
                || VehicleTag.TRUCK.getTag().equals(qName)
                || VehicleTag.BUS.getTag().equals(qName)) {
            vehicleSet.add(currentVehicle);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).strip();
        if (currentVehicleTag != null) {
            switch (currentVehicleTag) {
                case BRAND -> currentVehicle.setBrand(value);
                case MODEL -> currentVehicle.setModel(value);
                case ORIGIN -> currentVehicle.setOrigin(value);
                case PRICE -> currentVehicle.setPrice(Integer.parseInt(value));
                case CHARS -> currentVehicle.setChars(new Chars());
                case FUEL_TYPE -> currentVehicle.getChars().setFuelType(value);
                case POWER -> currentVehicle.getChars().setPower(Integer.parseInt(value));
                case FUEL_CONSUMPTION -> currentVehicle.getChars().setFuelConsumption(Double.parseDouble(value));
                case TRANSMISSION -> currentVehicle.getChars().setTransmission(value);
                case PARAMETERS -> currentVehicle.setParameters(new Parameters());
                case LENGTH -> currentVehicle.getParameters().setLength(Double.parseDouble(value));
                case WIDTH -> currentVehicle.getParameters().setWidth(Double.parseDouble(value));
                case WEIGHT -> currentVehicle.getParameters().setWeight(Integer.parseInt(value));
                case AWD -> ((Car) currentVehicle).setAwd(Boolean.parseBoolean(value));
                case ACCELERATION -> ((Car) currentVehicle).setAcceleration(Double.parseDouble(value));
                case MAX_SPEED -> ((Car) currentVehicle).setMaxSpeed(Integer.parseInt(value));
                case CARGO_CAPACITY -> ((Truck) currentVehicle).setCargoCapacity(Integer.parseInt(value));
                case PASSENGER_CAPACITY -> ((Bus) currentVehicle).setPassengerCapacity(Integer.parseInt(value));
                default -> {
                    logger.log(Level.WARN, "Unexpected tag!");
                    throw new EnumConstantNotPresentException(currentVehicleTag.getDeclaringClass(),
                            currentVehicleTag.name());
                }
            }
        }
        currentVehicleTag = null;
    }
}
