package cuit.pymjl.core.exception;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 18:33
 **/
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
