package by.gaponenko.xmlparser.builder.stax;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.builder.VehicleTag;
import by.gaponenko.xmlparser.entity.*;
import by.gaponenko.xmlparser.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VehiclesStAXBuilder extends AbstractVehicleBuilder {
    static Logger logger = LogManager.getLogger();

    private XMLInputFactory factory;

    public VehiclesStAXBuilder() {
        vehicleSet = new HashSet<>();
        factory = XMLInputFactory.newInstance();
    }

    public VehiclesStAXBuilder(Set<Vehicle> vehicleSet) {
        super(vehicleSet);
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildVehicles(String filePath) throws ProjectException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    Optional<VehicleTag> currentTug = VehicleTag.getVehicleTagByValue(name);
                    if (currentTug.isPresent()) {
                        if (VehicleTag.CAR.equals(currentTug.get())) {
                            Vehicle vehicle = buildVehicle(reader, new Car());
                            vehicleSet.add(vehicle);
                        }
                        if (VehicleTag.TRUCK.equals(currentTug.get())) {
                            Vehicle vehicle = buildVehicle(reader, new Truck());
                            vehicleSet.add(vehicle);
                        }
                        if (VehicleTag.BUS.equals(currentTug.get())) {
                            Vehicle vehicle = buildVehicle(reader, new Bus());
                            vehicleSet.add(vehicle);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File error or I/O error: " + e.getMessage());
            throw new ProjectException(e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    private Vehicle buildVehicle(XMLStreamReader reader, Vehicle vehicle) throws XMLStreamException, ProjectException {
        vehicle.setVehicleId(reader.getAttributeValue(null, VehicleTag.ID.getTag()));
        String description = reader.getAttributeValue(null, VehicleTag.DESCRIPTION.getTag());
        if (description != null) {
            vehicle.setDescription(description);
        } else {
            vehicle.setDescription("Description...");
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (VehicleTag.getVehicleTagByValue(name).get()) {
                        case BRAND -> vehicle.setBrand(getXMLText(reader));
                        case MODEL -> vehicle.setModel(getXMLText(reader));
                        case ORIGIN -> vehicle.setOrigin(getXMLText(reader));
                        case PRICE -> vehicle.setPrice(Integer.parseInt(getXMLText(reader)));
                        case CHARS -> vehicle.setChars(buildVehicleChars(reader));
                        case PARAMETERS -> vehicle.setParameters(buildVehicleParameters(reader));
                        case AWD -> ((Car) vehicle).setAwd(Boolean.parseBoolean(getXMLText(reader)));
                        case ACCELERATION -> ((Car) vehicle).setAcceleration(Double.parseDouble(getXMLText(reader)));
                        case MAX_SPEED -> ((Car) vehicle).setMaxSpeed(Integer.parseInt(getXMLText(reader)));
                        case CARGO_CAPACITY -> ((Truck) vehicle).setCargoCapacity(Integer.parseInt(getXMLText(reader)));
                        case PASSENGER_CAPACITY -> ((Bus) vehicle).setPassengerCapacity(Integer.parseInt(getXMLText(reader)));
                        default -> logger.log(Level.WARN, "Unexpected tag!");
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (VehicleTag.CAR.equals(VehicleTag.getVehicleTagByValue(name).get())
                            || VehicleTag.TRUCK.equals(VehicleTag.getVehicleTagByValue(name).get())
                            || VehicleTag.BUS.equals(VehicleTag.getVehicleTagByValue(name).get())) {
                        return vehicle;
                    }
                }
            }
        }
        throw new ProjectException("Unknown element in tag Vehicle!");
    }

    private Chars buildVehicleChars(XMLStreamReader reader) throws XMLStreamException, ProjectException {
        Chars chars = new Chars();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (VehicleTag.getVehicleTagByValue(name).get()) {
                        case FUEL_TYPE -> chars.setFuelType(getXMLText(reader));
                        case POWER -> chars.setPower(Integer.parseInt(getXMLText(reader)));
                        case FUEL_CONSUMPTION -> chars.setFuelConsumption(Double.parseDouble(getXMLText(reader)));
                        case TRANSMISSION -> chars.setTransmission(getXMLText(reader));
                        default -> logger.log(Level.WARN, "Unexpected tag!");
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(VehicleTag.CHARS.getTag())) {
                        return chars;
                    }
                }
            }
        }
        throw new ProjectException("Unknown element in tag Chars!");
    }

    private Parameters buildVehicleParameters(XMLStreamReader reader) throws XMLStreamException, ProjectException {
        Parameters parameters = new Parameters();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (VehicleTag.getVehicleTagByValue(name).get()) {
                        case LENGTH -> parameters.setLength(Double.parseDouble(getXMLText(reader)));
                        case WIDTH -> parameters.setWidth(Double.parseDouble(getXMLText(reader)));
                        case WEIGHT -> parameters.setWeight(Integer.parseInt(getXMLText(reader)));
                        default -> logger.log(Level.WARN, "Unexpected tag!");
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(VehicleTag.PARAMETERS.getTag())) {
                        return parameters;
                    }
                }
            }
        }
        throw new ProjectException("Unknown element in tag Chars!");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
