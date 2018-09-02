package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.models.reports.CampaignSummary;
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
import java.util.Date;
import java.util.List;

@RequestScoped
@Path("/report/campaign")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportCampaignResource {

    @Inject
    private CampaignDBService campaignDB;

    @Inject
    private ExceptionTransformer exceptionTransformer;

    @GET
    @Path("/{id}/ads")
    @Operation(summary = "Get campaign summary with ads", tags = {"Reports", "Campaign"},
            description = "Get a summary of a campaign with given id with ad details",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignSummary.class)
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
    public Response getCampaignSummaryWithAds(
            @PathParam("id") Long id
    ) {
        try {
            CampaignSummary summary = campaignDB.getCampaignReportWithAds(id);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/date/")
    @Operation(summary = "Get all campaign summaries from time period", tags = {"Reports", "Campaign"},
            description = "Get a summary of all campaigns in the database in provided time period (default = one week).",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getCampaignSummary(
            @QueryParam("dateFrom") Date dateFrom,
            @QueryParam("dateTo") Date dateTo
    ) {
        try {
            List<CampaignSummary> summary = campaignDB.getCampaignReportsByDate(dateFrom, dateTo);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/day/")
    @Operation(summary = "Get all campaign summaries from time period", tags = {"Reports", "Campaign"},
            description = "Get a summary of all campaigns in the database in provided time period (default = one week).",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
            List<CampaignSummary> summary = campaignDB.getCampaignReportByDay(dateFrom, dateTo);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/ongoing/")
    @Operation(summary = "Get all campaign summaries from time period", tags = {"Reports", "Campaign"},
            description = "Get a summary of all campaigns in the database in provided time period (default = one week).",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getOngoingCampaignSummary(
            @QueryParam("dateFrom") Date dateFrom,
            @QueryParam("dateTo") Date dateTo
    ) {
        try {
            List<CampaignSummary> summary = campaignDB.getOngoingCampaignReport();
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/ongoing/day/")
    @Operation(summary = "Get all ongoing campaign summaries by day.", tags = {"Reports", "Campaign"},
            description = "Get a summary of all ongoing campaigns in the database ",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getOngoingCampaignSummaryByDay(
            @QueryParam("dateFrom") Date dateFrom,
            @QueryParam("dateTo") Date dateTo
    ) {
        try {
            List<CampaignSummary> summary = campaignDB.getOngoingCampaignReportByDay();
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/finished/")
    @Operation(summary = "Get all finished campaign summaries.", tags = {"Reports", "Campaign"},
            description = "Get a summary of all finished campaigns in the database.",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getFinishedCampaignSummary() {
        try {
            List<CampaignSummary> summary = campaignDB.getFinishedCampaignReport();
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/finished/day/")
    @Operation(summary = "Get finished campaign summaries from time period", tags = {"Reports", "Campaign"},
            description = "Get a summary of all finished campaigns in the database.",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getFinishedCampaignSummaryByDay() {
        try {
            List<CampaignSummary> summary = campaignDB.getFinishedCampaignReportByDay();
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/id/{id}/")
    @Operation(summary = "Get campaign summary by id", tags = {"Reports", "Campaign"},
            description = "Get a summary of a campaign with a provided id",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignSummary.class)
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
            @PathParam("id") long id
    ) {
        try {
            CampaignSummary summary = campaignDB.getCampaignReportById(id);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/id/")
    @Operation(summary = "Get campaign summary", tags = {"Reports", "Campaign"},
            description = "Get a summary of a campaign",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema( schema = @Schema(implementation = CampaignSummary.class))
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
    public Response getCampaignSummary(
            @QueryParam("ids") List<Long> ids
    ) {
        try {
            List<CampaignSummary> summary = campaignDB.getCampaignReportByIdList(ids);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/name/{name}/")
    @Operation(summary = "Get campaign summary by name", tags = {"Reports", "Campaign"},
            description = "Get a summary of a campaign with a provided name",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignSummary.class)
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
            CampaignSummary summary = campaignDB.getCampaignReportByName(name);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/name/")
    @Operation(summary = "Get campaign summary by name list", tags = {"Reports", "Campaign"},
            description = "Get a summary of campaigns with provided list of names",
            responses = {
                    @ApiResponse(
                            description = "Summary data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema( schema = @Schema(implementation = CampaignSummary.class))
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
            @QueryParam("name") List<String> name
    ) {
        try {
            List<CampaignSummary> summary = campaignDB.getCampaignReportByNameList(name);
            return Response.ok(summary).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(exceptionTransformer.transformToDTO(e, 404)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
