package vendingmachine;

public class ChangeNotAvailableException extends Exception {
    public ChangeNotAvailableException(String message) {
        super(message);
    }
}
