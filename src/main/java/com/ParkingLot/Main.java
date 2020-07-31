package com.ParkingLot;

import com.ParkingLot.Constants.ParkingLotConstants;
import com.ParkingLot.Exceptions.ExceptionConstants;
import com.ParkingLot.Exceptions.ParkingLotException;
import com.ParkingLot.InputHandling.InputHandlingImpl;
import com.ParkingLot.InputHandling.InputHandlingService;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("---Parking Lot ----");
        InputHandlingService inputHandlingService = new InputHandlingImpl();
        switch (args.length) {
            case 0:
                while (true) {
                    printStartingCases();

                    Scanner in = new Scanner(System.in);
                    String input = in.nextLine();
                    input = input.trim();
                    try {
                        if (inputHandlingService.isInputValid(input)) {
                            inputHandlingService.startExecution(input);
                        } else {
                            throw new ParkingLotException(ExceptionConstants.INVALID_INPUTS.getMessage());
                        }

                    } catch (NumberFormatException exception){
                        System.out.println("The Format is not correct for "+exception.getMessage());
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }

            case 1: // File input/output
                parseFileInput(args[0]);
                break;
        }

    }
    public static void parseFileInput(String filePath) {
        // Assuming input to be a valid file path.
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            InputHandlingService inputHandlingService = new InputHandlingImpl();
            String input;
            try {
                while ((input = br.readLine()) != null) {
                    try {
                        if (inputHandlingService.isInputValid(input)) {
                            inputHandlingService.startExecution(input);
                        } else {
                            throw new ParkingLotException(ExceptionConstants.INVALID_INPUTS.getMessage());
                        }
                    }
                    catch (NumberFormatException exception){
                        System.out.println("The Format is not correct "+exception.getMessage());
                    }
                    catch (ParkingLotException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (IOException ex) {
                System.out.println(ExceptionConstants.ERROR_READING_INPUT_FILE.getMessage());
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(ExceptionConstants.FILE_NOT_FOUND.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Printing the initial user driven options
     */
    private static void printStartingCases() {
        String displayMessage = "";
        // Printing out the various options the user has
        displayMessage =
                "         Please type the commands for the instructions you want to do " + "\n\n" +
                        "          Action                                                   COMMAND" + "\n\n" +
                        "| To create a parking lot of size capacity          create_parking_lot {capacity}                            " + "\n" +
                        "| To park a car with Regno. and color               park <<car RegNo>> {car color}                           " + "\n" +
                        "| Unpark a car                                      leave {slot_number}                                      " + "\n" +
                        "| Print status                                      status                                                   " + "\n" +
                        "| Get regno for given car color                     registration_numbers_for_cars_with_colour {car color}    " + "\n" +
                        "| Get slot nos for given car color                  slot_numbers_for_cars_with_colour {car_color}            " + "\n" +
                        "| Get slot no for given car no.                     slot_number_for_registration_number {car_number}         " + "\n" +
                        "| Exit                                              exit" + "\n";

        System.out.println(displayMessage);
    }
}
