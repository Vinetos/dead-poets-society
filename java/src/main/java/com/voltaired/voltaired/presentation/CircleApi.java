package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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

    @GET
    @Operation(summary = "Get all the circles",
            description = "Returns a list of all the circles")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getAllCircles.Response[].class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    List<getAllCircles.Response> getAllCircles();

    @Operation(summary = "Create a circle",
            description = "Returns the created circle")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            createCircle.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @POST createCircle.Response createCircle(createCircle.Request request);

    @Operation(summary = "Get a circle",
            description = "Returns the circle")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getCircle.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @GET @Path("{id}") getCircle.Response getCircle(@PathParam("id") Long id);

    @Operation(summary = "Enter in a circle", description = "Returns the list of the circles ids of the user.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            enterCircle.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @PUT @Path("{id}/enter") enterCircle.Response enterCircle(@PathParam("id") Long id, enterCircle.Request request);

    @Operation(summary = "Leave in a circle", description = "Returns the list of the circles ids of the user.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            leaveCircle.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @PUT @Path("{id}/leave") leaveCircle.Response leaveCircle(@PathParam("id") Long id, leaveCircle.Request request);

    @Operation(summary = "Post a letter in a circle", description = "Returns the letter posted in the circle.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            postLetter.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @POST @Path("{id}/letters") postLetter.Response postLetter(@PathParam("id") Long id, postLetter.Request request);

    @Operation(summary = "Post a letter reply in a circle (NOT IMPLEMENTED)", description = "Returns the letter reply"
                                                                                            + " posted in the circle.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            postLettersReply.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @POST @Path("{id}/letters/{letterId}") postLettersReply.Response postLetterReply(@PathParam("id") Long id,
                                                                                     @PathParam("letterId") Long letterId,
                                                                                     postLettersReply.Request request);

    @Operation(summary = "Delete a circle", description = "Returns nothing.")
    @APIResponse(responseCode = "204", description = "The circle has been deleted")
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @DELETE @Path("{id}") void deleteCircles(@PathParam("id") Long id);

    interface createCircle {
        @Schema(name = "createCircle.Request")
        @With @Data @AllArgsConstructor @NoArgsConstructor class Request {
            public String name;
        }

        @Schema(name = "createCircle.Response")
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
        }
    }

    interface getAllCircles {
        @Schema(name = "getAllCircles.Response", description = "The response to a getAllCircles request, contains the"
                                                               + " list of all the circles")
        @With @Data @AllArgsConstructor class Response {
            @Schema(name = "id", description = "The Id of the circle", example = "8")
            public Long id;
            public String name;
            public List<Long> lettersId;
            public List<Long> writersId;
        }
    }

    interface getCircle {
        @Schema(name = "getCircle.Response")
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public List<Long> lettersId;
            public List<Long> writersId;
        }
    }

    interface enterCircle {
        @Schema(name = "enterCircle.Request")
        @With @Data @AllArgsConstructor @NoArgsConstructor class Request {
            public Long writerId;
        }

        @Schema(name = "enterCircle.Response")
        @With @Data @AllArgsConstructor class Response {
            public List<Long> circlesId;
        }
    }

    interface leaveCircle {
        @Schema(name = "leaveCircle.Request")
        @With @Data @AllArgsConstructor @NoArgsConstructor class Request {
            public Long writerId;
        }

        @Schema(name = "leaveCircle.Response")
        @With @Data @AllArgsConstructor class Response {
            public List<Long> circlesId;
        }
    }

    interface postLetter {
        @Schema(name = "postLetter.Request")
        @With @Data @AllArgsConstructor class Request {
            public String subject;
            public String content;
            public Long writerId;
        }

        @Schema(name = "postLetter.Response")
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
}
