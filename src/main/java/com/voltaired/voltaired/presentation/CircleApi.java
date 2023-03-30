package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/circles")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface CircleApi {

    @GET List<getAllCircles.Response> getAllActivities();

    @GET @Path("{id}") getCircle.Response getCircle(@PathParam("id") Long id);

    interface getAllCircles {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public Set<Long> lettersId;
            public Long writerId;
        }
    }

    interface getCircle {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public Set<Long> lettersId;
            public Long writerId;
        }
    }

}
