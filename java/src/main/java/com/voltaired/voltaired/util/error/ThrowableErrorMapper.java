package com.voltaired.voltaired.util.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableErrorMapper implements ExceptionMapper<Throwable> {

    @Override public Response toResponse(final Throwable exception) {
        exception.printStackTrace();
        if (exception instanceof ErrorCode.RuntimeError)
            return ((ErrorCode.RuntimeError) exception).asResponse();

        if (exception instanceof WebApplicationException)
            return ((WebApplicationException) exception).getResponse();

        return Response.status(500).build();
    }
}

