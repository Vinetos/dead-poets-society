package com.voltaired.voltaired.presentation;

import io.quarkus.security.Authenticated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/circles")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface CircleApi {

    @GET List<getAllCircles.Response> getAllActivities();

    @GET @Path("{id}") getCircle.Response getCircle(@PathParam("id") Long id);

    @POST @Authenticated
    @Path("{circleId}/letters") postLetters.Response postLetters(@PathParam("circleId") Long circleid, postLetters.Request request);

    interface getAllCircles {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public List<Long> lettersId;
            public List<Long> writersId;
        }
    }

    interface getCircle {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public List<Long> lettersId;
            public List<Long> writersId;
        }
    }

    interface postLetters {
        @With @Data @AllArgsConstructor class Request {
            public String subject;
            public String content;
            public Long writerId;
        }

        @With @Data @AllArgsConstructor class Response {
            public Long letterId;
            public ZonedDateTime date;
            public Long circlesIds;
            public String subject;
            public String content;
            public Long writerId;
        }
    }

}
