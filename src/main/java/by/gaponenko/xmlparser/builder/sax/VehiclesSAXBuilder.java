package by.gaponenko.xmlparser.builder.sax;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.entity.Vehicle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VehiclesSAXBuilder extends AbstractVehicleBuilder {
    static Logger logger = LogManager.getLogger();

    private VehicleHandler handler;
    private VehicleErrorHandler errorHandler;
    private SAXParserFactory factory;
    private XMLReader reader;
    private SAXParser saxParser;

    public VehiclesSAXBuilder() {
        vehicleSet = new HashSet<>();
        handler = new VehicleHandler();
        errorHandler = new VehicleErrorHandler();
        try {
            factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        reader.setContentHandler(handler);
        reader.setErrorHandler(errorHandler);
    }

    public VehiclesSAXBuilder(Set<Vehicle> vehicleSet) {
        super(vehicleSet);
        handler = new VehicleHandler();
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        reader.setContentHandler(handler);
    }

    @Override
    public void buildVehicles(String filePath) {
        try {
            reader.parse(filePath);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parse error!", e.getMessage());
        }
        vehicleSet = handler.getVehicleSet();
    }
}
