package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/letters")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface LetterApi {

    @GET List<getAllLetters.Response> getAllLetters();

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
