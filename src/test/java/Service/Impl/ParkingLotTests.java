package Service.Impl;

import Constants.ParkingLotConstants;
import Exceptions.ParkingLotException;
import InputHandling.InputHandlingImpl;
import InputHandling.InputHandlingService;
import Service.ParkingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingLotTests {

    private final ByteArrayOutputStream outContent	= new ByteArrayOutputStream();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init()
    {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp()
    {
        System.setOut(null);
    }

    @Test
    public void testCreateParkingLot() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(1000);
        assertTrue(("Created a parking lot with 1000 slots").equals(outContent.toString().trim()));
        parkingService.closeProgram();
    }

    @Test
    public void testAlreadyCreatedParkingLot() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(10);
        assertTrue(("Created a parking lot with 10 slots").equals(outContent.toString().trim()));
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ParkingLotConstants.PARKINGLOT_ALREADY_CREATED));
        parkingService.createParkingLot(5);
        parkingService.closeProgram();
    }


    @Test
    public void carAlreadyPresent() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(10);
        assertTrue(("Created a parking lot with 10 slots").equals(outContent.toString().trim()));
        outContent.reset();
        parkingService.park("KA-01-HH-1234","White");
        assertTrue(("Allocated slot number: 1").equals(outContent.toString().trim()));
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ParkingLotConstants.CAR_ALREADY_PRESENT));
        parkingService.park("KA-01-HH-1234","White");
        parkingService.closeProgram();
    }

    @Test
    public void parkingWithoutParkingLot() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.closeProgram();
    }

    public void ifParkingLotExist(ParkingService parkingService){
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ParkingLotConstants.PARKING_LOT_NOT_CREATED));
    }

    @Test
    public void testFullParkingLot() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(2);
        assertTrue(("Created a parking lot with 2 slots").equals(outContent.toString().trim()));
        outContent.reset();
        parkingService.park("KA-01-HH-1234","White");
        assertTrue(("Allocated slot number: 1").equals(outContent.toString().trim()));
        outContent.reset();
        parkingService.park("KA-01-HH-1235","White");
        assertTrue(("Allocated slot number: 2").equals(outContent.toString().trim()));
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ParkingLotConstants.PARKINGLOT_IS_FULL));
        parkingService.park("KA-01-HH-1237","White");
        parkingService.closeProgram();
    }

    @Test
    public void leaveCar() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.leave(4);
        parkingService.createParkingLot(10);
        outContent.reset();
        parkingService.leave(1);
        assertTrue(("Slot number 1 is free").equals(outContent.toString().trim()));
        parkingService.closeProgram();
    }

    @Test
    public void leaveCarSlotWhichIsEmpty() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(10);
        outContent.reset();
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ParkingLotConstants.NO_CAR_PRESENT+"1"));
        parkingService.leave(1);
        parkingService.closeProgram();
    }

    @Test
    public void testStatus() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.printStatus();
        parkingService.createParkingLot(3);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.park("KA-01-HH-9999","White");
        parkingService.park("KA-01-BB-0001","Black");
        parkingService.printStatus();
        assertTrue(("Slot No. Registration No\n" +
                "Color\n" +
                "1 KA-01-HH-1234 White\n" +
                "2 KA-01-HH-9999 White\n" +
                "3 KA-01-BB-0001 Black").trim().replace(" ","").
                equals(outContent.toString().trim().replace(" ","")));
        outContent.reset();
        parkingService.closeProgram();
    }

    @Test
    public void testGetCarsRegNoWithColor() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.getCarsRegWithColor("White");
        parkingService.createParkingLot(3);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.park("KA-01-HH-9999","White");
        parkingService.park("KA-01-BB-0001","Black");
        parkingService.getCarsRegWithColor("White");
        assertTrue(("KA-01-HH-1234, KA-01-HH-9999").trim().replace(" ","").
                equals(outContent.toString().trim().replace(" ","")));
        outContent.reset();
        parkingService.closeProgram();
    }

    @Test
    public void testGetCarsSlotWithColor() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.getCarsSlotWithColor("White");
        parkingService.createParkingLot(3);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.park("KA-01-HH-9999","White");
        parkingService.park("KA-01-BB-0001","Black");
        parkingService.getCarsSlotWithColor("White");
        assertTrue(("1,2").trim().replace(" ","").
                equals(outContent.toString().trim().replace(" ","")));
        outContent.reset();
        parkingService.closeProgram();
    }

    @Test
    public void testGetSlotForReg() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        ifParkingLotExist(parkingService);
        parkingService.getSlotForReg("KA-01-HH-1234");
        parkingService.createParkingLot(4);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.park("KA-01-HH-9999","White");
        parkingService.park("KA-01-BB-0001","Black");
        parkingService.park("KA-01-BB-0002","Black");
        parkingService.getSlotForReg("KA-01-HH-1234");
        assertTrue(("1").trim().
                equals(outContent.toString().trim()));
        outContent.reset();
        parkingService.closeProgram();
    }

    @Test
    public void testInvalidInput()  {
        InputHandlingService inputHandlingService = new InputHandlingImpl();
        assertFalse(inputHandlingService.isInputValid("create_parking_lot -1"));
        assertFalse(inputHandlingService.isInputValid("status car"));
        assertFalse(inputHandlingService.isInputValid("park KA-01-HH-1234 Black NotReqd"));
        assertFalse(inputHandlingService.isInputValid("registration_numbers_for_cars_with"));

    }


}