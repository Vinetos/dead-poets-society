package com.voltaired.voltaired.presentation.resource;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/users")
@Authenticated
public class UserResource {

    @Inject SecurityIdentity securityIdentity;

    @GET
    @Path("/me")
    public String me() {
        return securityIdentity.getPrincipal().getName();
    }

}
