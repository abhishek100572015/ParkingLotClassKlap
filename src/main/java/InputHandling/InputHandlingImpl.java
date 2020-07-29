package InputHandling;

import Constants.ParkingLotConstants;
import Exceptions.ParkingLotException;
import Service.Impl.ParkingServiceImpl;
import Service.ParkingService;

public class InputHandlingImpl implements InputHandlingService{

    ParkingService parkingService = new ParkingServiceImpl();

    @Override
    public boolean isInputValid(String input) {
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
                    if (sizeOfSlot<0){
                        return false;
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
                if(splitInput.length != 1){
                    return false;
                }
                break;
            default: {
                return false;
            }
        }
        return true;
    }

    public void startExecution(String input) throws ParkingLotException{
        String splitInput[] = input.split(" ");
        switch (splitInput[0]){
            case ParkingLotConstants.CREATE_PARKING_LOT:
            {
                try {
                    int sizeOfLot = Integer.parseInt(splitInput[1]);
                    parkingService.createParkingLot(sizeOfLot);
                    break;
                }
                catch (NumberFormatException exception){
                    exception.printStackTrace();
                }
                catch (ParkingLotException exception){
                    exception.printStackTrace();
                }
            }
            case ParkingLotConstants.PARK:
            {
                    String carRegNo = (splitInput[1]);
                    String color = splitInput[2];
                    parkingService.park(carRegNo, color);
                    break;
            }
            case ParkingLotConstants.LEAVE:
            {
                try{
                int slotNumber = Integer.parseInt(splitInput[1]);
                parkingService.leave(slotNumber);
                break;}
                catch (NumberFormatException exception){
                    exception.printStackTrace();
                }
            }
            case ParkingLotConstants.STATUS:
            {
                parkingService.printStatus();
                break;
            }
            case ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR:
            {
                String color = splitInput[1];
                parkingService.getCarsRegWithColor(color);
                break;
            }
            case ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
            {
                String color = splitInput[1];
                parkingService.getCarsSlotWithColor(color);
                break;
            }
            case ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER:
            {
                String reg_no = splitInput[1];
                parkingService.getSlotForReg(reg_no);
                break;
            }
            case ParkingLotConstants.EXIT:
            {
                parkingService.closeProgram();
                System.exit(0);

            }
            default:
            {
                throw new ParkingLotException("Inavlid Input");
            }
        }
    }
}
