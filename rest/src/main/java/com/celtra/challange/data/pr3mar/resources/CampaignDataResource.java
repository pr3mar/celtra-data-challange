package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.CampaignDTO;
import com.celtra.challange.data.pr3mar.services.db.CampaignDBService;
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
@Path("/data/campaign/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampaignDataResource {

    @Inject
    ExceptionTransformer exceptionTransformer;

    @Inject
    CampaignDBService db;

    @GET
    @Operation(summary = "Get all campaigns", tags = {"Campaign"},
            description = "Returns a list of all campaigns",
            responses = {
                    @ApiResponse(
                            description = "Campaign data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignDTO.class))
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
            List<CampaignDTO> dtos = db.findAll();
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
    @Operation(summary = "Get campaign by id", tags = {"Campaign"},
            description = "Returns the campaign with provided id, if exists.",
            responses = {
                    @ApiResponse(
                            description = "Campaign data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignDTO.class)
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
            CampaignDTO dto = db.findById(id);
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
    @Operation(summary = "Create a new campaign", tags = {"Campaign"},
            description = "Returns the object of the created campaign.",
            responses = {
                    @ApiResponse(
                            description = "Campaign data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignDTO.class)
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
            CampaignDTO instance
    ) {
        try {
            CampaignDTO dto = db.createNew(instance);
            return Response.ok(dto).build();
        } catch (InvalidParameterException | InvalidEntityException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
