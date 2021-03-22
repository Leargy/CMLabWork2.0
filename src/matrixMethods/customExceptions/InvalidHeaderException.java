package matrixMethods.customExceptions;

public class InvalidHeaderException extends Exception {
    private String message;

    public InvalidHeaderException (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
