package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Set;

@Path("/circles")
public interface CircleApi {

    @GET @Path("/") List<getAllCircles.Response> getAllActivities();

    @GET @Path("/{id}") getAllCircles.Response getActivity(@PathParam("id") id);

    interface getAllCircles {
        @With @Data @AllArgsConstructor class Response {
            public Long id;
            public String name;
            public Set<Long> lettersId;
            public Long writerId;
        }
    }

}
