package com.voltaired.voltaired.util.auth;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@PreMatching
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    @Inject AuthenticationContextImpl authCtx; // Injecting the implementation,
    // not the interface!!!

    @Context SecurityContext securityCtx;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        authCtx.setCurrentUser(new User() {

            @Override public String getUsername() {
                return requestContext.getHeaderString("OIDC_CLAIM_preferred_username");
            }

            @Override public String getEmail() {
                return requestContext.getHeaderString("OIDC_CLAIM_email");
            }

            @Override public String getFamilyName() {
                return requestContext.getHeaderString("OIDC_CLAIM_family_name");
            }

            @Override public String getFirstName() {
                return requestContext.getHeaderString("OIDC_CLAIM_name");
            }
        });
    }
}
