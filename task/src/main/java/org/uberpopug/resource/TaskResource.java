package org.uberpopug.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.uberpopug.resource.pojo.task.CreateTaskRequest;
import org.uberpopug.service.TaskService;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    @Path("/createTask")
    @RolesAllowed({"Admin", "User"})
    public Response createTask(CreateTaskRequest request) {
        taskService.createTask(request);
        return Response.ok().build();
    }

}
