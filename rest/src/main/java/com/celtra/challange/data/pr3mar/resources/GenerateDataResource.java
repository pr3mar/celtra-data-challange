package com.celtra.challange.data.pr3mar.resources;

import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.models.dto.AdDTO;
import com.celtra.challange.data.pr3mar.models.dto.ImpressionDTO;
import com.celtra.challange.data.pr3mar.services.GenerateDataService;
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
@Path("/data/generate/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GenerateDataResource {

    @Inject
    GenerateDataService dataGenerator;

    @GET
    @Operation(summary = "Generate new data", tags = {"Generate"},
            description = "Generates new data with provided constraints. It always creates at least 10 new (campaigns|ads|impressions)." +
                    "It creates new users just in case the provided amount is greater than the one already existing in the database." +
                    "The generator uniformly at random assigns an ad to a campaign, and uniformly at random chooses a user for an impression." +
                    "Each user can have at most 10 impressions per ad." +
                    "Returns a list of generated impressions",
            responses = {
                    @ApiResponse(
                            description = "Ad data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ImpressionDTO.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    public Response generateData(
            @QueryParam("intervalFrom") Date from,
            @QueryParam("intervalTo") Date to,
            @QueryParam("numberOfCampaigns") Long numberOfCampaigns,
            @QueryParam("numberOfAds") Long numberOfAds,
            @QueryParam("numberOfImpressions") Long numberOfImpressions,
            @QueryParam("numberOfUsers") Long numberOfUsers
    ) {
        try {
            List<ImpressionDTO> data = dataGenerator.generateNewData(from, to, numberOfCampaigns, numberOfAds, numberOfImpressions, numberOfUsers);
            return Response.ok(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
