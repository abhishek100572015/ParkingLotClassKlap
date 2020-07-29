package Service.Impl;

import Constants.ParkingLotConstants;
import Exceptions.ParkingLotException;
import Objects.Car;
import Service.ParkingService;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ParkingServiceImpl implements ParkingService {

    ArrayList<Car> parkingLot;
    PriorityQueue<Integer> emptySlotsQueue;
    int sizeOfLot;


    @Override
    public void createParkingLot(int sizeOfLot) throws ParkingLotException{

        if(parkingLot != null){
            throw new ParkingLotException(ParkingLotConstants.PARKINGLOT_ALREADY_CREATED);
        }
        parkingLot = new ArrayList<>(sizeOfLot+1);;
        emptySlotsQueue = new PriorityQueue<Integer>();
        this.sizeOfLot = sizeOfLot;
        initialize(sizeOfLot);
        System.out.println("Created a parking lot with "+sizeOfLot+" slots\n");

    }

    private boolean isParkingLotIsCreated() throws ParkingLotException{
        if(parkingLot == null){
            throw new ParkingLotException(ParkingLotConstants.PARKING_LOT_NOT_CREATED);
        }
       return true;
    }


    private void initialize(int sizeOfLot) {
        for(int i= 1;i<=sizeOfLot;i++){
            parkingLot.add(null);
            emptySlotsQueue.add(i);
        }
    }

    @Override
    public void park(String regNo, String color) throws ParkingLotException{
        if(isParkingLotIsCreated()) {
            if (emptySlotsQueue.isEmpty()) {
                throw new ParkingLotException(ParkingLotConstants.PARKINGLOT_IS_FULL);
            } else if (isCarWithRegNoPresent(regNo)) {
                throw new ParkingLotException(ParkingLotConstants.CAR_ALREADY_PRESENT);
            } else {
                int emptySlot = emptySlotsQueue.poll();
                parkingLot.add(emptySlot, new Car(regNo, color));
                System.out.println("Allocated slot number: "+emptySlot);
            }
        }
    }

    private boolean isCarWithRegNoPresent(String regNo) {
        for(int i=0;i<parkingLot.size();i++){
            if(parkingLot.get(i) != null && (parkingLot.get(i).getRegNo()).equals(regNo)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void leave(int slotNumber) throws ParkingLotException{
        if(isParkingLotIsCreated()) {
            if (slotNumber > sizeOfLot || slotNumber <= 0) {
                throw new ParkingLotException("Slot Number is invalid");
            } else if (parkingLot.get(slotNumber) == null) {
                throw new ParkingLotException(ParkingLotConstants.NO_CAR_PRESENT + slotNumber);
            } else {

                parkingLot.set(slotNumber, null);
                emptySlotsQueue.add(slotNumber);
                System.out.println("Slot number " + slotNumber + " is free" + "\n");
            }
        }
    }

    @Override
    public void printStatus() throws ParkingLotException {
        if(isParkingLotIsCreated()) {
            System.out.println("Slot No   Registration Number    Color");
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot.get(i) != null) {
                    int slotNo = i;
                    String regNo = parkingLot.get(i).getRegNo();
                    String color = parkingLot.get(i).getColor();
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
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot.get(i) != null) {
                    String regNo = parkingLot.get(i).getRegNo();
                    String carColor = parkingLot.get(i).getColor();
                    if (carColor.equals(color)) {
                        System.out.print(seperator + regNo);
                        seperator = ",";
                    }
                }
            }
        }
    }

    @Override
    public void getCarsSlotWithColor(String color) throws ParkingLotException {
        if(isParkingLotIsCreated()) {
            String seperator = "";
            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot.get(i) != null) {
                    int slotNo = i;
                    String carColor = parkingLot.get(i).getColor();
                    if (carColor.equals(color)) {
                        System.out.print(seperator + slotNo);
                        seperator = ",";
                    }
                }
            }
        }
    }

    @Override
    public void getSlotForReg(String reg_no) throws ParkingLotException {
        if(isParkingLotIsCreated()) {

            for (int i = 1; i <= sizeOfLot; i++) {
                if (parkingLot.get(i) != null) {
                    int slotNo = i;
                    String carRegNo = parkingLot.get(i).getRegNo();
                    if (carRegNo.equals(reg_no)) {
                        System.out.print(slotNo);
                        return;
                    }
                }
            }
            throw new ParkingLotException(ParkingLotConstants.CAR_NOT_FOUND);
        }
    }

    @Override
    public void closeProgram() {
        parkingLot.clear();
        emptySlotsQueue.clear();
    }
}
