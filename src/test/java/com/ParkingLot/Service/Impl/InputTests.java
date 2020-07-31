package com.ParkingLot.Service.Impl;

import com.ParkingLot.Exceptions.ParkingLotException;
import com.ParkingLot.InputHandling.InputHandlingImpl;
import com.ParkingLot.InputHandling.InputHandlingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;

public class InputTests {
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
    public void testInvalidInput() throws ParkingLotException {
        InputHandlingService inputHandlingService = new InputHandlingImpl();
        assertFalse(inputHandlingService.isInputValid("status car"));
        assertFalse(inputHandlingService.isInputValid("park KA-01-HH-1234 Black NotReqd"));
        assertFalse(inputHandlingService.isInputValid("registration_numbers_for_cars_with"));
        assertFalse(inputHandlingService.isInputValid("To check any Invalid Inputs"));

    }
}
