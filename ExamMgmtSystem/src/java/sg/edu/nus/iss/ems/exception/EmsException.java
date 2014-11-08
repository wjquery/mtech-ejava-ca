package sg.edu.nus.iss.ems.exception;

public class EmsException extends RuntimeException {

    private static final long serialVersionUID = -8990063747624973000L;

    public EmsException() {
        super();
    }

    public EmsException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmsException(String message) {
        super(message);
    }

    public EmsException(Throwable cause) {
        super(cause);
    }
}
