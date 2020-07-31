package com.ParkingLot.Service.Impl;

import com.ParkingLot.Constants.ParkingLotConstants;
import com.ParkingLot.Exceptions.ExceptionConstants;
import com.ParkingLot.Exceptions.ParkingLotException;
import com.ParkingLot.Model.Car;
import com.ParkingLot.Service.ParkingService;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *Is the implementation of ParkingService and contains the code for the execution
 */
public class ParkingServiceImpl implements ParkingService {

    Car[] parkingLot;
    PriorityQueue<Integer> emptySlotsQueue;
    int sizeOfLot;

    private boolean isParkingLotIsCreated() throws ParkingLotException{
        if(parkingLot == null){
            throw new ParkingLotException(ExceptionConstants.PARKING_LOT_NOT_CREATED.getMessage());
        }
        return true;
    }

    /**
     * Initializing the array from 1 to map to the slots present in real world.As slot number cant be zero.
     * emptySlotsQueue contains the empty slots at any present time.
     * @param sizeOfLot
     */

    private void initialize(int sizeOfLot) {
        for(int i = 1;i<=sizeOfLot;i++){
            parkingLot[i]=null;
            emptySlotsQueue.add(i);
        }
    }
    
    @Override
    public void createParkingLot(int sizeOfLot) throws ParkingLotException{

        if(parkingLot != null){
            throw new ParkingLotException(ExceptionConstants.PARKINGLOT_ALREADY_CREATED.getMessage());
        }
        parkingLot = new Car[sizeOfLot+1];
        emptySlotsQueue = new PriorityQueue<Integer>();
        this.sizeOfLot = sizeOfLot;
        initialize(sizeOfLot);
        System.out.println("Created a parking lot with "+sizeOfLot+" slots\n");

    }


    @Override
    public void park(String regNo, String color) throws ParkingLotException{
        if(isParkingLotIsCreated()) {
            if (emptySlotsQueue.isEmpty()) {
                throw new ParkingLotException(ExceptionConstants.PARKINGLOT_IS_FULL.getMessage());
            } else if (isCarWithRegNoPresent(regNo)) {
                throw new ParkingLotException(ExceptionConstants.CAR_ALREADY_PRESENT.getMessage());
            } else {
                int emptySlot = emptySlotsQueue.poll();
                parkingLot[emptySlot] =new Car(regNo, color);
                System.out.println("Allocated slot number: "+emptySlot);
            }
        }
    }

    private boolean isCarWithRegNoPresent(String regNo) {
        for(int i=1;i<parkingLot.length;i++){
            if(parkingLot[i] != null && (parkingLot[i].getRegNo()).equals(regNo)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void leave(int slotNumber) throws ParkingLotException{
        if(isParkingLotIsCreated()) {
            if (slotNumber > sizeOfLot || slotNumber <= 0) {
                throw new ParkingLotException(ExceptionConstants.SLOT_NUMBER_IS_INVALID.getMessage());
            } else if (parkingLot[slotNumber] == null) {
                throw new ParkingLotException(ExceptionConstants.NO_CAR_PRESENT.getMessage() + slotNumber);
            } else {

                parkingLot[slotNumber]=null;
                emptySlotsQueue.add(slotNumber);
                System.out.println("Slot number " + slotNumber + " is free" + "\n");
            }
        }
    }

    @Override
    public void printStatus() throws ParkingLotException {
        if(isParkingLotIsCreated()) {
            System.out.println("Slot No.   Registration No    Color");
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot[i] != null) {
                    int slotNo = i;
                    String regNo = parkingLot[i].getRegNo();
                    String color = parkingLot[i].getColor();
                    System.out.println(slotNo + "\t\t\t\t" + regNo + "\t\t\t\t" + color);
                }
            }
            System.out.println();
        }
    }

    @Override
    public void getCarsRegWithColor(String color) throws ParkingLotException {
        if(isParkingLotIsCreated()) {
            String seperator = "";
            int cnt = 0;
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot[i] != null) {
                    String regNo = parkingLot[i].getRegNo();
                    String carColor = parkingLot[i].getColor();
                    if (carColor.equalsIgnoreCase(color)) {
                        System.out.print(seperator + regNo);
                        seperator = ",";
                        cnt++;
                    }
                }
            }
            if (cnt==0){
                System.out.println(ParkingLotConstants.CAR_NOT_FOUND);
            }
        }
        System.out.println();
    }

    @Override
    public void getCarsSlotWithColor(String color) throws ParkingLotException {
        if(isParkingLotIsCreated()) {
            int cnt = 0;
            String seperator = "";
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot[i] != null) {
                    int slotNo = i;
                    String carColor = parkingLot[i].getColor();
                    if (carColor.equalsIgnoreCase(color)) {
                        System.out.print(seperator + slotNo);
                        seperator = ",";
                        cnt++;
                    }
                }
            }
            if(cnt==0){
                System.out.println(ParkingLotConstants.CAR_NOT_FOUND);
            }
        }
        System.out.println();
    }

    @Override
    public void getSlotForReg(String reg_no) throws ParkingLotException {
        if(isParkingLotIsCreated()) {

            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot[i] != null) {
                    int slotNo = i;
                    String carRegNo = parkingLot[i].getRegNo();
                    if (carRegNo.equals(reg_no)) {
                        System.out.print(slotNo);
                        System.out.println();
                        return;
                    }
                }
            }
            System.out.println(ParkingLotConstants.CAR_NOT_FOUND);
        }
    }

    @Override
    public void closeProgram() {
        emptySlotsQueue.clear();
    }
}
