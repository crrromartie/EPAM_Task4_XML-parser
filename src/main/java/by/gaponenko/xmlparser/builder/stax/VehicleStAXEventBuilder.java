package by.gaponenko.xmlparser.builder.stax;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.builder.VehicleTag;
import by.gaponenko.xmlparser.entity.*;
import by.gaponenko.xmlparser.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VehicleStAXEventBuilder extends AbstractVehicleBuilder {
    static Logger logger = LogManager.getLogger();

    XMLInputFactory factory;

    public VehicleStAXEventBuilder() {
        vehicleSet = new HashSet<>();
        factory = XMLInputFactory.newInstance();
    }

    public VehicleStAXEventBuilder(Set<Vehicle> vehicleSet) {
        super(vehicleSet);
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildVehicles(String filePath) throws ProjectException {
        Vehicle vehicle = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XMLEventReader reader = factory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(VehicleTag.CAR.getTag())
                            || startElement.getName().getLocalPart().equals(VehicleTag.TRUCK.getTag())
                            || startElement.getName().getLocalPart().equals(VehicleTag.BUS.getTag())) {
                        if (startElement.getName().getLocalPart().equals(VehicleTag.CAR.getTag())) {
                            vehicle = new Car();
                        } else if (startElement.getName().getLocalPart().equals(VehicleTag.TRUCK.getTag())) {
                            vehicle = new Truck();
                        } else if (startElement.getName().getLocalPart().equals(VehicleTag.BUS.getTag())) {
                            vehicle = new Bus();
                        }
                        Attribute id = startElement.getAttributeByName(new QName(VehicleTag.ID.getTag()));
                        vehicle.setVehicleId(id.getValue());
                        Attribute description = startElement.getAttributeByName(new QName(VehicleTag.DESCRIPTION.getTag()));
                        if (description != null) {
                            vehicle.setDescription(description.getValue());
                        } else {
                            vehicle.setDescription("Description...");
                        }
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.BRAND.getTag())) {
                        event = reader.nextEvent();
                        vehicle.setBrand(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.MODEL.getTag())) {
                        event = reader.nextEvent();
                        vehicle.setModel(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.ORIGIN.getTag())) {
                        event = reader.nextEvent();
                        vehicle.setOrigin(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.PRICE.getTag())) {
                        event = reader.nextEvent();
                        vehicle.setPrice(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.CHARS.getTag())) {
                        Chars chars = new Chars();
                        vehicle.setChars(chars);
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.FUEL_TYPE.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getChars().setFuelType(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.POWER.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getChars().setPower(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.FUEL_CONSUMPTION.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getChars().setFuelConsumption(Double.parseDouble(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.TRANSMISSION.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getChars().setTransmission(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.PARAMETERS.getTag())) {
                        Parameters parameters = new Parameters();
                        vehicle.setParameters(parameters);
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.LENGTH.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getParameters().setLength(Double.parseDouble(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.WIDTH.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getParameters().setWidth(Double.parseDouble(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.WEIGHT.getTag())) {
                        event = reader.nextEvent();
                        vehicle.getParameters().setWeight(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.AWD.getTag())) {
                        event = reader.nextEvent();
                        ((Car) vehicle).setAwd(Boolean.parseBoolean(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.ACCELERATION.getTag())) {
                        event = reader.nextEvent();
                        ((Car) vehicle).setAcceleration(Double.parseDouble(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.MAX_SPEED.getTag())) {
                        event = reader.nextEvent();
                        ((Car) vehicle).setMaxSpeed(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.CARGO_CAPACITY.getTag())) {
                        event = reader.nextEvent();
                        ((Truck) vehicle).setCargoCapacity(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(VehicleTag.PASSENGER_CAPACITY.getTag())) {
                        event = reader.nextEvent();
                        ((Bus) vehicle).setPassengerCapacity(Integer.parseInt(event.asCharacters().getData()));
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(VehicleTag.CAR.getTag())
                            || endElement.getName().getLocalPart().equals(VehicleTag.TRUCK.getTag())
                            || endElement.getName().getLocalPart().equals(VehicleTag.BUS.getTag())) {
                        vehicleSet.add(vehicle);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.FATAL, "File not found! " + e.getMessage());
            throw new ProjectException("File not found!", e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File error or I/O error: " + e.getMessage());
            throw new ProjectException("IOException!", e);
        } catch (XMLStreamException e) {
            throw new ProjectException(e);
        }
    }
}
