package by.gaponenko.xmlparser.builder.dom;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.builder.VehicleTag;
import by.gaponenko.xmlparser.entity.*;
import by.gaponenko.xmlparser.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VehiclesDOMBuilder extends AbstractVehicleBuilder {
    static Logger logger = LogManager.getLogger();

    private DocumentBuilder documentBuilder;

    public VehiclesDOMBuilder() {
        this.vehicleSet = new HashSet<>();
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public VehiclesDOMBuilder(Set<Vehicle> vehicleSet) {
        super(vehicleSet);
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    @Override
    public void buildVehicles(String filePath) throws ProjectException {
        File file = new File(filePath);
        try {
            Document document = documentBuilder.parse(file);
            Element root = document.getDocumentElement();
            NodeList vehicleList = root.getChildNodes();
            for (int i = 0; i < vehicleList.getLength(); i++) {
                if (vehicleList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element vehicleElement = (Element) vehicleList.item(i);
                    Vehicle vehicle = buildVehicle(vehicleElement);
                    vehicleSet.add(vehicle);
                }
            }
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parsing failure: " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR, "File error or I/O error: " + e.getMessage());
            throw new ProjectException(e);
        }
    }

    private Vehicle buildVehicle(Element element) {
        Vehicle vehicle = null;
        if (element.getTagName().equals(VehicleTag.CAR.getTag())) {
            vehicle = buildCar(element);
        }
        if (element.getTagName().equals(VehicleTag.TRUCK.getTag())) {
            vehicle = buildTruck(element);
        }
        if (element.getTagName().equals(VehicleTag.BUS.getTag())) {
            vehicle = buildBus(element);
        }
        vehicle.setVehicleId(element.getAttribute(VehicleTag.ID.getTag()));
        String description = element.getAttribute(VehicleTag.DESCRIPTION.getTag());
        if (!description.isEmpty()) {
            vehicle.setDescription(description);
        } else {
            vehicle.setDescription("Description...");
        }
        vehicle.setBrand(getElementTextContent(element, VehicleTag.BRAND.getTag()));
        vehicle.setModel(getElementTextContent(element, VehicleTag.MODEL.getTag()));
        vehicle.setOrigin(getElementTextContent(element, VehicleTag.ORIGIN.getTag()));
        vehicle.setPrice(Integer.parseInt(getElementTextContent(element, VehicleTag.PRICE.getTag())));
        vehicle.setChars(buildVehicleChars(element));
        vehicle.setParameters(buildVehicleParameters(element));
        return vehicle;
    }

    private Chars buildVehicleChars(Element element) {
        Chars chars = new Chars();
        chars.setFuelType(getElementTextContent(element, VehicleTag.FUEL_TYPE.getTag()));
        chars.setPower(Integer.parseInt(getElementTextContent(element, VehicleTag.POWER.getTag())));
        chars.setFuelConsumption(Double.parseDouble(getElementTextContent(element, VehicleTag.FUEL_CONSUMPTION.getTag())));
        chars.setTransmission(getElementTextContent(element, VehicleTag.TRANSMISSION.getTag()));
        return chars;
    }

    private Parameters buildVehicleParameters(Element element) {
        Parameters parameters = new Parameters();
        parameters.setLength(Double.parseDouble(getElementTextContent(element, VehicleTag.LENGTH.getTag())));
        parameters.setWidth(Double.parseDouble(getElementTextContent(element, VehicleTag.WIDTH.getTag())));
        parameters.setWeight(Integer.parseInt(getElementTextContent(element, VehicleTag.WEIGHT.getTag())));
        return parameters;
    }

    private Car buildCar(Element element) {
        Car car = new Car();
        car.setAwd(Boolean.parseBoolean(getElementTextContent(element, VehicleTag.AWD.getTag())));
        car.setAcceleration(Double.parseDouble(getElementTextContent(element, VehicleTag.ACCELERATION.getTag())));
        car.setMaxSpeed(Integer.parseInt(getElementTextContent(element, VehicleTag.MAX_SPEED.getTag())));
        return car;
    }

    private Truck buildTruck(Element element) {
        Truck truck = new Truck();
        truck.setCargoCapacity(Integer.parseInt(getElementTextContent(element, VehicleTag.CARGO_CAPACITY.getTag())));
        return truck;
    }

    private Bus buildBus(Element element) {
        Bus bus = new Bus();
        bus.setPassengerCapacity(Integer.parseInt(getElementTextContent(element, VehicleTag.PASSENGER_CAPACITY.getTag())));
        return bus;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
