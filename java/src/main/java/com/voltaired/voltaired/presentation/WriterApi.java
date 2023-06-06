package com.voltaired.voltaired.presentation;

import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/writers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface WriterApi {

    @Operation(summary = "Get all writers", description = "Returns all the writers.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getAllWriters.Response[].class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @GET @Path("/") List<WriterApi.getAllWriters.Response> getAllWriters();

    @Operation(summary = "Get a writer", description = "Returns the writer from the id.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            getWriter.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @GET @Path("/{id}") getWriter.Response getWriter(@PathParam("id") Long id);

    @Operation(summary = "Create a writer", description = "Returns the created writer.")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
            createWriter.Response.class)))
    @APIResponse(description = "Invalid JWT token", responseCode = "401")
    @APIResponse(description = "Missing permissions to perform this action", responseCode = "403")
    @POST @Path("/") createWriter.Response createWriter(createWriter.Request request);

    interface createWriter {
        @With
        @Data
        @AllArgsConstructor
        class Response {
            public long id;
            public String name;
            public String penName;
            public String title;
        }

        @With
        @Data
        @AllArgsConstructor
        class Request {
            public String name;
            public String penName;
            public String title;
        }
    }

    interface getAllWriters {
        @With
        @Data
        @AllArgsConstructor
        class Response {
            public long id;
            public String title;
            public String name;
            public String penName;

            public List<LetterEntity> letters;
            public List<CircleEntity> circles;
        }
    }

    interface getWriter {
        @With
        @Data
        @AllArgsConstructor
        class Response {
            public long id;
            public String title;
            public String name;
            public String penName;

            public List<LetterEntity> letters;
            public List<CircleEntity> circles;
        }
    }

}
