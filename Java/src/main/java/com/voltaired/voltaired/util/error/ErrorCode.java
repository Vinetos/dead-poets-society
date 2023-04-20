package com.voltaired.voltaired.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ErrorCode extends Supplier<ErrorCode.RuntimeError>, Function<Object[], ErrorCode.RuntimeError> {

    default RuntimeError apply(final Object[] parameters) {
        return new RuntimeError(this, parameters);
    }

    default RuntimeError get() {
        return runtime();
    }

    int getHttpCode();

    String getMessage(final Object... parameters);

    default RuntimeError runtime(final Object... parameters) {
        return new RuntimeError(this, parameters);
    }

    default RuntimeError runtime() {
        return new RuntimeError(this, null);
    }

    default Supplier<RuntimeError> with(final Object... parameters) {
        return () -> apply(parameters);
    }

    @Getter @AllArgsConstructor class RuntimeError extends RuntimeException {
        public ErrorCode errorCode;
        public Object[] parameters;

        public Response asResponse() {
            return Response.status(getHttpCode())
                           .entity("{\"error\":\"" + getMessage() + "\"}")
                           .build();
        }

        public int getHttpCode() {
            return errorCode.getHttpCode();
        }

        @Override public String getMessage() {
            return errorCode.getMessage(parameters);
        }
    }
}

