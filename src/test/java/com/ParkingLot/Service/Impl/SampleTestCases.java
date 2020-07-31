package com.ParkingLot.Service.Impl;

import com.ParkingLot.Exceptions.ParkingLotException;
import com.ParkingLot.Service.Impl.Constants.Constants;
import com.ParkingLot.Service.ParkingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class SampleTestCases {
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
    public void testSampleCases() throws ParkingLotException {
        ParkingService parkingService = new ParkingServiceImpl();
        parkingService.createParkingLot(6);
        parkingService.park("KA-01-HH-1234","White");
        parkingService.park("KA-01-HH-9999","White");
        parkingService.park("KA-01-BB-0001","Black");
        parkingService.park("KA-01-HH-7777","Red");
        parkingService.park("KA-01-HH-2701","Blue");
        parkingService.park("KA-01-HH-3141","Black");
        parkingService.leave(4);
        parkingService.printStatus();
        parkingService.park("KA-01-P-333", "White");
        parkingService.getCarsRegWithColor("White");
        parkingService.getCarsSlotWithColor("White");
        parkingService.getSlotForReg("KA-01-HH-3141");
        parkingService.getSlotForReg("MH-04-AY-1111");
        assertTrue(Constants.outPutTestCase1.trim().replace(" ","").
                equalsIgnoreCase(outContent.toString().trim().replace(" ","")));


    }

}
