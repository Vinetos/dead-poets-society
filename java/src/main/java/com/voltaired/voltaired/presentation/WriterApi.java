package com.voltaired.voltaired.presentation;

import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/writers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface WriterApi {

    @GET
    @Path("/")
    List<WriterApi.getAllWriters.Response> getAllWriters();

    @GET
    @Path("/{id}")
    WriterApi.getWriter.Response getWriter(@PathParam("id") Long id);

    @POST
    @Path("/") createWriter.Response createWriter(createWriter.Request request);

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
