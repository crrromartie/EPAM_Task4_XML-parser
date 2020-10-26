package test.by.gaponenko.xmlparser.builder.stax;

import by.gaponenko.xmlparser.builder.AbstractVehicleBuilder;
import by.gaponenko.xmlparser.entity.*;
import by.gaponenko.xmlparser.exception.ProjectException;
import by.gaponenko.xmlparser.factory.VehicleBuilderFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class VehicleStAXEventBuilderTest {
    VehicleBuilderFactory factory;
    AbstractVehicleBuilder builder;

    @BeforeClass
    public void setUp() throws ProjectException {
        factory = new VehicleBuilderFactory();
        builder = factory.createVehicleBuilder("stax_event");
    }

    @Test(dataProvider = "dataForPositiveTest")
    public void VehiclesDOMBuilderPositiveTest(Set<Vehicle> expected) throws ProjectException{
        builder.buildVehicles("src/main/resources/data/dataForTest.xml");
        Set<Vehicle> actual = builder.getVehicleSet();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForNegativeTest")
    public void VehiclesDOMBuilderNegativeTest(Set<Vehicle> expected) throws ProjectException {
        builder.buildVehicles("src/main/resources/data/dataForTest.xml");
        Set<Vehicle> actual = builder.getVehicleSet();
        Assert.assertNotEquals(actual, expected);
    }

    @DataProvider(name = "dataForPositiveTest")
    public Object[] dataForPositiveTest() {
        Set<Vehicle> vehicles = new HashSet<>();
        Vehicle vehicle1 = new Car();
        vehicle1.setVehicleId("Id-1");
        vehicle1.setBrand("Audi");
        vehicle1.setModel("A4");
        vehicle1.setOrigin("Germany");
        vehicle1.setPrice(36000);
        Chars chars1 = new Chars();
        chars1.setTransmission("automatic");
        chars1.setFuelConsumption(5.8);
        chars1.setFuelType("diesel");
        chars1.setPower(194);
        vehicle1.setChars(chars1);
        Parameters parameters1 = new Parameters();
        parameters1.setLength(4.56);
        parameters1.setWidth(1.86);
        parameters1.setWeight(2000);
        vehicle1.setParameters(parameters1);
        ((Car) vehicle1).setAwd(true);
        ((Car) vehicle1).setAcceleration(8.2);
        ((Car) vehicle1).setMaxSpeed(225);
        vehicle1.setDescription("Description...");
        vehicles.add(vehicle1);
        Vehicle vehicle2 = new Truck();
        vehicle2.setVehicleId("Id-2");
        vehicle2.setBrand("Fiat");
        vehicle2.setModel("Ducato");
        vehicle2.setOrigin("Italy");
        vehicle2.setPrice(27000);
        Chars chars2 = new Chars();
        chars2.setTransmission("manual");
        chars2.setFuelConsumption(8.3);
        chars2.setFuelType("diesel");
        chars2.setPower(180);
        vehicle2.setChars(chars2);
        Parameters parameters2 = new Parameters();
        parameters2.setLength(5.83);
        parameters2.setWidth(1.88);
        parameters2.setWeight(4000);
        vehicle2.setParameters(parameters2);
        ((Truck) vehicle2).setCargoCapacity(1500);
        vehicle2.setDescription("Description...");
        vehicles.add(vehicle2);
        Vehicle vehicle3 = new Bus();
        vehicle3.setVehicleId("Id-3");
        vehicle3.setBrand("Ford");
        vehicle3.setModel("Transit");
        vehicle3.setOrigin("USA");
        vehicle3.setPrice(27000);
        Chars chars3 = new Chars();
        chars3.setTransmission("manual");
        chars3.setFuelConsumption(8.8);
        chars3.setFuelType("diesel");
        chars3.setPower(125);
        vehicle3.setChars(chars3);
        Parameters parameters3 = new Parameters();
        parameters3.setLength(6.5);
        parameters3.setWidth(1.9);
        parameters3.setWeight(4600);
        vehicle3.setParameters(parameters3);
        ((Bus) vehicle3).setPassengerCapacity(17);
        vehicle3.setDescription("Description...");
        vehicles.add(vehicle3);
        return new Set[]{vehicles};
    }

    @DataProvider(name = "dataForNegativeTest")
    public Object[] dataForNegativeTest() {
        Set<Vehicle> vehicles = new HashSet<>();
        Vehicle vehicle1 = new Car();
        vehicle1.setVehicleId("Id-1");
        vehicle1.setBrand("Audi");
        vehicle1.setModel("A4");
        vehicle1.setOrigin("Germany");
        vehicle1.setPrice(36000);
        Chars chars1 = new Chars();
        chars1.setTransmission("DSG");
        chars1.setFuelConsumption(5.8);
        chars1.setFuelType("diesel");
        chars1.setPower(194);
        vehicle1.setChars(chars1);
        Parameters parameters1 = new Parameters();
        parameters1.setLength(4.56);
        parameters1.setWidth(1.86);
        parameters1.setWeight(2000);
        vehicle1.setParameters(parameters1);
        ((Car) vehicle1).setAwd(true);
        ((Car) vehicle1).setAcceleration(8.2);
        ((Car) vehicle1).setMaxSpeed(225);
        vehicle1.setDescription("Description...");
        vehicles.add(vehicle1);
        Vehicle vehicle2 = new Truck();
        vehicle2.setVehicleId("Id-2");
        vehicle2.setBrand("Fiat");
        vehicle2.setModel("Ducato");
        vehicle2.setOrigin("Belarus");
        vehicle2.setPrice(27000);
        Chars chars2 = new Chars();
        chars2.setTransmission("manual");
        chars2.setFuelConsumption(8.3);
        chars2.setFuelType("diesel");
        chars2.setPower(180);
        vehicle2.setChars(chars2);
        Parameters parameters2 = new Parameters();
        parameters2.setLength(5.83);
        parameters2.setWidth(1.88);
        parameters2.setWeight(4000);
        vehicle2.setParameters(parameters2);
        ((Truck) vehicle2).setCargoCapacity(1500);
        vehicle2.setDescription("Description...");
        vehicles.add(vehicle2);
        Vehicle vehicle3 = new Bus();
        vehicle3.setVehicleId("Id-3");
        vehicle3.setBrand("Ford");
        vehicle3.setModel("Transit");
        vehicle3.setOrigin("USA");
        vehicle3.setPrice(27000);
        Chars chars3 = new Chars();
        chars3.setTransmission("manual");
        chars3.setFuelConsumption(8.8);
        chars3.setFuelType("diesel");
        chars3.setPower(125);
        vehicle3.setChars(chars3);
        Parameters parameters3 = new Parameters();
        parameters3.setLength(6.5);
        parameters3.setWidth(1.9);
        parameters3.setWeight(4600);
        vehicle3.setParameters(parameters3);
        ((Bus) vehicle3).setPassengerCapacity(17);
        vehicle3.setDescription("");
        vehicles.add(vehicle3);
        return new Set[]{vehicles};
    }

    @Test(expectedExceptions = ProjectException.class)
    public void throwProjectExceptionTest() throws ProjectException {
        builder.buildVehicles("src/main/resources/data/daaForTest.xml");
    }

    @AfterClass
    public void tearDown() {
        factory = null;
        builder = null;
    }
}
