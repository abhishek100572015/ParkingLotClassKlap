package com.ParkingLot.Service;

import com.ParkingLot.Exceptions.ParkingLotException;

/**
 * Various operations that can be done in the ParkingLot
 */
public interface ParkingService {
    void createParkingLot(int sizeOfLot) throws ParkingLotException;

    void park(String RegNo, String color) throws ParkingLotException;

    void leave(int slotNumber) throws ParkingLotException;

    void printStatus() throws ParkingLotException;

    void getCarsRegWithColor(String color) throws ParkingLotException;

    void getCarsSlotWithColor(String color) throws ParkingLotException;

    void getSlotForReg(String reg_no) throws ParkingLotException;

    void closeProgram();
}
