package com.ParkingLot.InputHandling;

import com.ParkingLot.Exceptions.ParkingLotException;

/**
 * Various input operations that need to be performed.
 */
public interface InputHandlingService {
    boolean isInputValid(String input) throws ParkingLotException;

    void startExecution(String input) throws ParkingLotException;
}
