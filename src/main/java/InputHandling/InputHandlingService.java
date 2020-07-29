package InputHandling;

import Exceptions.ParkingLotException;

public interface InputHandlingService {
    boolean isInputValid(String input);

    void startExecution(String input) throws ParkingLotException;
}
