package org.uberpopug.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.uberpopug.resource.pojo.authorization.TokenRequest;
import org.uberpopug.service.AuthorizationService;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorizationResource {

    @Inject
    AuthorizationService authorizationService;

    public String getToken(TokenRequest tokenRequest) {
        return authorizationService.getToken(tokenRequest);
    }
}