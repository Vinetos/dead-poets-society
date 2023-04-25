package com.voltaired.voltaired.presentation;

import io.quarkus.security.Authenticated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/circles") @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON) public interface CircleApi {

    @GET List<getAllCircles.Response> getAllActivities();

    @GET @Path("{id}") getCircle.Response getCircle(@PathParam("id") Long id);

    @PUT
    @Authenticated
    @Path("{id}/enter") enterCircle.Response enterCircle(@PathParam("id") Long id, enterCircle.Request request);

    @PUT
    @Authenticated
    @Path("{id}/leave") void leaveCircle(@PathParam("id") Long id, leaveCircle.Request request);

    @POST
    @Authenticated
    @Path("{id}/letters") postLetters.Response postLetters(@PathParam("id") Long id, postLetters.Request request);

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

    interface enterCircle {
        @With @Data @AllArgsConstructor class Request {
            public Long writerId;
        }

        @With @Data @AllArgsConstructor class Response {
            public List<Long> circlesId;
        }
    }

    interface leaveCircle {
        @With @Data @AllArgsConstructor class Request {
            public Long writerId;
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
