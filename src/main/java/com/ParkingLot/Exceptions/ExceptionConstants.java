package com.ParkingLot.Exceptions;

public enum ExceptionConstants {
    PARKINGLOT_ALREADY_CREATED("Parking Lot Already created. Cant be created again"),
    PARKINGLOT_IS_FULL("Sorry, parking lot is full"),
    CAR_ALREADY_PRESENT("Car with RegNo Already Present"),
    PARKING_LOT_NOT_CREATED("Parking Lot Not Created Yet"),
    NO_CAR_PRESENT("No car present at the slot "),
    INVALID_INPUTS("Invalid Inputs!! Please enter again"),
    ERROR_READING_INPUT_FILE("Error in reading the input file."),
    FILE_NOT_FOUND("File not found in the path specified."),
    SLOT_CANT_BE_LESS_THAN_ZERO("Slot cant be less than zero"),
    SLOT_NUMBER_IS_INVALID("Slot Number is invalid");

    private String message;

    ExceptionConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
