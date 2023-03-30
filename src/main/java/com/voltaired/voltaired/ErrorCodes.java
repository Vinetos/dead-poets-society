package com.voltaired.voltaired;

import com.voltaired.voltaired.util.ErrorCode;
import lombok.Getter;

public enum ErrorCodes implements ErrorCode {
    CIRCLE_NOT_FOUND(404, "The circle %s can not be found")
    ;

    public final String message;
    public @Getter final int httpCode;

    ErrorCodes(int httpCode, String message) {
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override public String getMessage(Object... parameters) {
        return String.format(message, parameters);
    }
}

