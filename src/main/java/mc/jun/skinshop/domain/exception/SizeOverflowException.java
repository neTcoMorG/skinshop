package mc.jun.skinshop.domain.exception;

public class SizeOverflowException extends RuntimeException {
    public SizeOverflowException() {
        super();
    }

    public SizeOverflowException(String message) {
        super(message);
    }

    public SizeOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public SizeOverflowException(Throwable cause) {
        super(cause);
    }

    protected SizeOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
