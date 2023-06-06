package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/letters")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface LetterApi {

    @Operation(summary = "Get all letters", description = "Returns all letters.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getAllLetters.Response[].class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @GET List<getAllLetters.Response> getAllLetters();

    @Operation(summary = "Get a letter", description = "Returns the letter from this id.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getLetter.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @GET @Path("{id}") getLetter.Response getLetter(@PathParam("id") Long id);
    interface getAllLetters {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public ZonedDateTime date;
            public String subject;
            public String content;
            public List<Long> circlesIds;
            public Long writerId;
        }
    }

    interface getLetter {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public ZonedDateTime date;
            public String subject;
            public String content;
            public List<Long> circlesIds;
            public Long writerId;
        }
    }

}
