package danekerscode.exception;

public class Base64OperationException extends RuntimeException {
    public Base64OperationException(String msg){
        super(msg);
    }

    public Base64OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
