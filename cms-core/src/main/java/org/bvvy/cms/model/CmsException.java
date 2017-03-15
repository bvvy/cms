package org.bvvy.cms.model;

public class CmsException extends RuntimeException {

    public CmsException() {
        super();
    }

    protected CmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CmsException(String message) {
        super(message);
    }

    public CmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CmsException(Throwable cause) {
        super(cause);
    }
}
