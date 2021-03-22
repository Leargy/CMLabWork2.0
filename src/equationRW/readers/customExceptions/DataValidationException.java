package equationRW.readers.customExceptions;

public class DataValidationException extends Exception {
    private String message;

    public DataValidationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
