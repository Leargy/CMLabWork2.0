package matrixMethods.customExceptions;

public class NonCompliaceException extends Exception{
    private String message;

    public NonCompliaceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
