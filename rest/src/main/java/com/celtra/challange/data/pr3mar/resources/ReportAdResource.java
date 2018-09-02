package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.models.reports.AdSummary;
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
import java.util.Date;
import java.util.List;

@RequestScoped
@Path("/report/ad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportAdResource {

    @Inject
    private AdDBService adDB;

    @Inject
    private ExceptionTransformer exceptionTransformer;

    @GET
    @Path("/date")
    @Operation(summary = "Get summary for ads in given time frame", tags = {"Reports"},
            description = "Get a summary of the ads within the given time frame (default = one week)",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdSummary.class)
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
    public Response getCampaignSummaryByDate(
            @QueryParam("dateFrom") Date dateFrom,
            @QueryParam("dateTo") Date dateTo
    ) {
        try {
            List<AdSummary> summary = adDB.getAdSummaryByDate(dateFrom, dateTo);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/day")
    @Operation(summary = "Get summary for all ads per day", tags = {"Reports"},
            description = "Get a summary of all ads per day in the given time period (default = one week)",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdSummary.class)
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
    public Response getCampaignSummaryByDay(
            @QueryParam("dateFrom") Date dateFrom,
            @QueryParam("dateTo") Date dateTo
    ) {
        try {
            List<AdSummary> summary = adDB.getAdSummaryByDay(dateFrom, dateTo);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/id")
    @Operation(summary = "Get summary for ads with ids", tags = {"Reports"},
            description = "Get a summary of ads with given ids from the system",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AdSummary.class))
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
    public Response getCampaignSummaryByIdList(
            @QueryParam("ids") List<Long> ids
    ) {
        try {
            List<AdSummary> summary = adDB.getAdSummaryByIdList(ids);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Get summary for ad with id", tags = {"Reports"},
            description = "Get a summary of ad with given id from the system",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdSummary.class)
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
    public Response getCampaignSummaryById(
            @PathParam("id") Long id
    ) {
        try {
            AdSummary summary = adDB.getAdSummaryById(id);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("name/{name}")
    @Operation(summary = "Get summary for ads by name", tags = {"Reports"},
            description = "Get a summary of the ad with the provided name from the system",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdSummary.class)
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
    public Response getCampaignSummaryByName(
            @PathParam("name") String name
    ) {
        try {
            AdSummary summary = adDB.getAdSummaryByName(name);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    @GET
    @Path("name/")
    @Operation(summary = "Get summary for ads by name list", tags = {"Reports"},
            description = "Get a summary of the ad with the provided names from the system",
            responses = {
                    @ApiResponse(
                            description = "AdSummary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AdSummary.class))
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
    public Response getCampaignSummaryByNameList(
            @QueryParam("names") List<String> names
    ) {
        try {
            List<AdSummary> summary = adDB.getAdSummaryByNameList(names);
            return Response.ok(summary).build();
        } /*catch (InvalidParameterException e) {
            return Response.status(400).entity(exceptionTransformer.transformToDTO(e, 400)).build();
        } */catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
