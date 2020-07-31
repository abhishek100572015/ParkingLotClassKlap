package com.ParkingLot.InputHandling;

import com.ParkingLot.Constants.ParkingLotConstants;
import com.ParkingLot.Exceptions.ExceptionConstants;
import com.ParkingLot.Exceptions.ParkingLotException;
import com.ParkingLot.Service.Impl.ParkingServiceImpl;
import com.ParkingLot.Service.ParkingService;

public class InputHandlingImpl implements InputHandlingService {

    ParkingService parkingService = new ParkingServiceImpl();

    /**
     * To Check whether the inut String entered is valid or not
     *
     * @param input
     * @return
     */
    @Override
    public boolean isInputValid(String input) throws NumberFormatException, ParkingLotException {
        String splitInput[] = input.split(" ");
        switch (splitInput[0]) {
            case ParkingLotConstants.PARK: {
                if (splitInput.length != 3) {
                    return false;
                }
                break;
            }
            case ParkingLotConstants.CREATE_PARKING_LOT:
            case ParkingLotConstants.LEAVE:
                if (splitInput.length != 2) {
                    return false;
                }

                int sizeOfSlot = Integer.parseInt(splitInput[1]);
                if (sizeOfSlot < 0) {

                    throw new ParkingLotException(ExceptionConstants.SLOT_CANT_BE_LESS_THAN_ZERO.getMessage());
                }
                break;
            case ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR:
            case ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
            case ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER: {
                if (splitInput.length != 2) {
                    return false;
                }
                break;
            }
            case ParkingLotConstants.STATUS:
            case ParkingLotConstants.EXIT:
                if (splitInput.length != 1) {
                    return false;
                }
                break;
            default: {
                return false;
            }
        }
        return true;
    }

    /**
     * Start the execution from the input String
     *
     * @param input
     * @throws ParkingLotException
     */
    public void startExecution(String input) throws ParkingLotException {
        String splitInput[] = input.split(" ");
        switch (splitInput[0]) {
            case ParkingLotConstants.CREATE_PARKING_LOT: {
                int sizeOfLot = Integer.parseInt(splitInput[1]);
                parkingService.createParkingLot(sizeOfLot);
                break;
            }
            case ParkingLotConstants.PARK: {
                String carRegNo = (splitInput[1]);
                String color = splitInput[2];
                parkingService.park(carRegNo, color);
                break;
            }
            case ParkingLotConstants.LEAVE: {
                try {
                    int slotNumber = Integer.parseInt(splitInput[1]);
                    parkingService.leave(slotNumber);
                    break;
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            case ParkingLotConstants.STATUS: {
                parkingService.printStatus();
                break;
            }
            case ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR: {
                String color = splitInput[1];
                parkingService.getCarsRegWithColor(color);
                break;
            }
            case ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR: {
                String color = splitInput[1];
                parkingService.getCarsSlotWithColor(color);
                break;
            }
            case ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER: {
                String reg_no = splitInput[1];
                parkingService.getSlotForReg(reg_no);
                break;
            }
            case ParkingLotConstants.EXIT: {
                parkingService.closeProgram();
                System.exit(0);
                break;

            }
            default: {
                throw new ParkingLotException(ExceptionConstants.INVALID_INPUTS.getMessage());
            }
        }
    }
}
