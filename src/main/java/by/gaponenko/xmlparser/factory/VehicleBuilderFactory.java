package by.gaponenko.xmlparser.factory;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.builder.dom.VehiclesDOMBuilder;
import by.gaponenko.xmlparser.builder.sax.VehiclesSAXBuilder;
import by.gaponenko.xmlparser.builder.stax.VehicleStAXEventBuilder;
import by.gaponenko.xmlparser.builder.stax.VehiclesStAXBuilder;

public class VehicleBuilderFactory {
    private enum TypeParser {
        SAX, DOM, STAX, STAX_EVENT
    }

    public AbstractVehicleBuilder createVehicleBuilder(String typeParser) {
        AbstractVehicleBuilder builder;
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX -> builder = new VehiclesSAXBuilder();
            case DOM -> builder = new VehiclesDOMBuilder();
            case STAX -> builder = new VehiclesStAXBuilder();
            case STAX_EVENT -> builder = new VehicleStAXEventBuilder();
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
        return builder;
    }
}
