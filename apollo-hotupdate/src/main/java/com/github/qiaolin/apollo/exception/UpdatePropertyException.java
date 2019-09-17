package com.github.qiaolin.apollo.exception;

/**
 *  更新属性异常
 * @author qiaolin
 */
public class UpdatePropertyException extends RuntimeException {

    public UpdatePropertyException() {
    }

    public UpdatePropertyException(String message) {
        super(message);
    }

    public UpdatePropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdatePropertyException(Throwable cause) {
        super(cause);
    }

    public UpdatePropertyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
