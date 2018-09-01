package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.ImpressionDTO;
import com.celtra.challange.data.pr3mar.models.dto.UserDTO;
import com.celtra.challange.data.pr3mar.services.db.UserDBService;
import com.celtra.challange.data.pr3mar.transformers.ExceptionTransformer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/data/user/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserDataResource {

    @Inject
    ExceptionTransformer exceptionTransformer;

    @Inject
    UserDBService db;

    @GET
    @Operation(summary = "Get all users", tags = {"User"},
            description = "Returns a list of all users",
            responses = {
                    @ApiResponse(
                            description = "User data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    public Response getAll() {
        try {
            List<UserDTO> dtos = db.findAll();
            return Response.ok(dtos).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get user by id", tags = {"User"},
            description = "Returns the user with provided id, if exists.",
            responses = {
                    @ApiResponse(
                            description = "User data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    public Response getById(
            @PathParam("id") long id
    ) {
        try {
            UserDTO dto = db.findById(id);
            return Response.ok(dto).build();
        } catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Operation(summary = "Create a new user", tags = {"User"},
            description = "Returns the object of the created user.",
            responses = {
                    @ApiResponse(
                            description = "User data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ImpressionDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    public Response createNew(
            UserDTO instance
    ) {
        try {
            UserDTO dto = db.createNew(instance);
            return Response.ok(dto).build();
        } catch (InvalidParameterException | InvalidEntityException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
