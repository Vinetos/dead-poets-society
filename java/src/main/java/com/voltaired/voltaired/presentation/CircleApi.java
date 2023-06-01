package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/circles") @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON) public interface CircleApi {

    @GET List<getAllCircles.Response> getAllCircles();

    @GET @Path("{id}") getCircle.Response getCircle(@PathParam("id") Long id);

    @PUT
    @Path("{id}/enter") enterCircle.Response enterCircle(@PathParam("id") Long id, enterCircle.Request request);

    @PUT
    @Path("{id}/leave") void leaveCircle(@PathParam("id") Long id, leaveCircle.Request request);

    @POST
    @Path("{id}/letters") postLetters.Response postLetters(@PathParam("id") Long id, postLetters.Request request);

    @POST
    @Path("{id}/letters/{letterId}") postLettersReply.Response postLetterReply(@PathParam("id") Long id,
                                                                               @PathParam("letterId") Long letterId,
                                                                               postLettersReply.Request request);

    @DELETE
    @Path("{id}") deleteCircles deleteCircles(@PathParam("id") Long id);

    @POST
    @Path("create/{name}") createCircle.Response createCircle(@PathParam("name") String name);

    interface createCircle {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
        }
    }

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

    interface postLettersReply {
        @With @Data @AllArgsConstructor class Request {
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

    interface deleteCircles {
        @With @Data @AllArgsConstructor class Request {

        }

        @With @Data @AllArgsConstructor class Response {

        }
    }

}
