package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.AdDTO;
import com.celtra.challange.data.pr3mar.services.db.AdDBService;
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
@Path("/data/ad/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdDataResource {

    @Inject
    ExceptionTransformer exceptionTransformer;

    @Inject
    AdDBService db;

    @GET
    @Operation(summary = "Get all ads", tags = {"Ad"},
            description = "Returns a list of all ads",
            responses = {
                    @ApiResponse(
                            description = "Ad data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AdDTO.class))
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
            List<AdDTO> dtos = db.findAll();
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
    @Operation(summary = "Get ad by id", tags = {"Ad"},
            description = "Returns the ad with provided id, if exists.",
            responses = {
                    @ApiResponse(
                            description = "Ad data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdDTO.class)
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
            AdDTO dto = db.findById(id);
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
    @Operation(summary = "Create a new ad", tags = {"Ad"},
            description = "Returns the object of the created ad.",
            responses = {
                    @ApiResponse(
                            description = "Ad data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdDTO.class)
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
            AdDTO instance
    ) {
        try {
            AdDTO dto = db.createNew(instance);
            return Response.ok(dto).build();
        } catch (InvalidParameterException | InvalidEntityException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
