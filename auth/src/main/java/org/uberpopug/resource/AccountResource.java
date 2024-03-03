package org.uberpopug.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.uberpopug.resource.pojo.account.CreateAccountRequest;
import org.uberpopug.resource.pojo.account.DeleteAccountRequest;
import org.uberpopug.resource.pojo.account.UpdateAccountRequest;
import org.uberpopug.service.AccountService;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @Path("/createAccount")
    @RolesAllowed({ "Admin" })
    public Response createAccount(CreateAccountRequest request) {
        accountService.createAccount(request);
        return Response.ok().build();
    }

    @Path("/updateAccount")
    @RolesAllowed({ "Admin" })
    public Response updateAccount(UpdateAccountRequest request) {
        accountService.updateAccount(request);
        return Response.ok().build();
    }

    @Path("/deleteAccount")
    @RolesAllowed({ "Admin" })
    public Response deleteAccount(DeleteAccountRequest request) {
        accountService.deleteAccount(request);
        return Response.ok().build();
    }

}
