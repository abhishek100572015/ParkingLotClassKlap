import Constants.ParkingLotConstants;
import Exceptions.ParkingLotException;
import InputHandling.InputHandlingImpl;
import InputHandling.InputHandlingService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParkingLotException {
        System.out.println("---Parking Lot ----");
        InputHandlingService inputHandlingService = new InputHandlingImpl();

        while(true){
            printStartingCases();

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();
                try {
                    if(inputHandlingService.isInputValid(input)){
                    inputHandlingService.startExecution(input);
                    }
                    else{
                        throw new ParkingLotException(ParkingLotConstants.INVALID_INPUTS);
                    }

                }
                catch (Exception exception){
                    System.out.println(ParkingLotConstants.INVALID_INPUTS);
                }
                finally {
                    in.close();
                }
            }

        }


    private static void printStartingCases()
    {
        String displayMessage = "";
        // Printing out the various options the user has
        displayMessage =
                "         Please type the commands for the instructions you want to do "+"\n\n"+
                        "          Action                                                   COMMAND"+"\n\n"+
            "| To create a parking lot of size capacity     |     create_parking_lot {capacity}                            |"+"\n"+
            "| To park a car with Regno. and color          |     park <<car RegNo>> {car color}                           |"+"\n"+
            "| Unpark a car                                 |     leave {slot_number}                                      |"+"\n"+
            "| Print status                                 |     status                                                   |"+"\n"+
            "| Get regno for given car color                |     registration_numbers_for_cars_with_color {car color}     |"+"\n"+
            "| Get slot nos for given car color             |     slot_numbers_for_cars_with_color {car_color}             |"+"\n"+
            "| Get slot no for given car no.                |     slot_number_for_registration_number {car_number}         |"+"\n"+
            "| Exit                                         |     exit"+"\n";

        System.out.println(displayMessage);
    }
}
