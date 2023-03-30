package com.voltaired.voltaired.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

@Path("/circles") public interface CircleApi {

    @GET @Path("/") List<getAllCircles.Response> getAllActivities();

    interface getAllCircles {
        @With @Data @AllArgsConstructor class Response {
            Long id;
            String name;
            Set<Long> lettersId;
            Long writerId;
        }
    }

}
