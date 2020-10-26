package by.gaponenko.xmlparser.builder;

import java.util.Arrays;
import java.util.Optional;

public enum VehicleTag {
    CAR("car"),
    TRUCK("truck"),
    BUS("bus"),
    BRAND("brand"),
    MODEL("model"),
    ORIGIN("origin"),
    PRICE("price"),
    ID("id"),
    DESCRIPTION("description"),
    CHARS("chars"),
    FUEL_TYPE("fuel-type"),
    POWER("power"),
    FUEL_CONSUMPTION("fuel-consumption"),
    TRANSMISSION("transmission"),
    PARAMETERS("parameters"),
    LENGTH("length"),
    WIDTH("width"),
    WEIGHT("weight"),
    AWD("awd"),
    ACCELERATION("acceleration"),
    MAX_SPEED("max-speed"),
    CARGO_CAPACITY("cargo-capacity"),
    PASSENGER_CAPACITY("passenger-capacity");

    private String tag;

    VehicleTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public static Optional<VehicleTag> getVehicleTagByValue(String value) {
        return Arrays.stream(VehicleTag.values()).filter(o -> o.getTag().equals(value)).findAny();
    }
}
