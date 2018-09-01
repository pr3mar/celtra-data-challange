package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.InteractionTypeDTO;
import com.celtra.challange.data.pr3mar.services.db.InteractionTypeDBService;
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
@Path("/data/interactionType/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InteractionTypeDataResource {

    @Inject
    ExceptionTransformer exceptionTransformer;

    @Inject
    InteractionTypeDBService db;

    @GET
    @Operation(summary = "Get all interaction types", tags = {"InteractionType"},
            description = "Returns a list of all interaction types",
            responses = {
                    @ApiResponse(
                            description = "Interaction Type data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = InteractionTypeDTO.class))
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
            List<InteractionTypeDTO> dtos = db.findAll();
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
    @Operation(summary = "Get interaction type by id", tags = {"InteractionType"},
            description = "Returns the interaction type with provided id, if exists.",
            responses = {
                    @ApiResponse(
                            description = "Interaction type data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InteractionTypeDTO.class)
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
            InteractionTypeDTO dto = db.findById(id);
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
    @Operation(summary = "Create a new interaction type", tags = {"InteractionType"},
            description = "Returns the object of the created Interaction type.",
            responses = {
                    @ApiResponse(
                            description = "Interaction type data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InteractionTypeDTO.class)
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
            InteractionTypeDTO instance
    ) {
        try {
            InteractionTypeDTO dto = db.createNew(instance);
            return Response.ok(dto).build();
        } catch (InvalidParameterException | InvalidEntityException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
