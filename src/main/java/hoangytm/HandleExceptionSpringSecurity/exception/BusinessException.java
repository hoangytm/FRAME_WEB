package hoangytm.HandleExceptionSpringSecurity.exception;

/**
 * @author PhanHoang
 * 3/4/2020
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = -2777373325976335319L;

    /**
     * Construct a new instance of {@code RestClientException} with the given message.
     *
     * @param msg the message
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * Construct a new instance of {@code RestClientException} with the given message and
     * exception.
     *
     * @param msg the message
     * @param ex  the exception
     */
    public BusinessException(String msg, Throwable ex) {
        super(msg, ex);
    }
}