package com.celtra.challange.data.pr3mar;

import com.celtra.challange.data.pr3mar.resources.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v1")
@OpenAPIDefinition(
        info = @Info(title = "Rest API", version = "v1", description = "Celtra Data Engineer REST API endpoints"),
//        servers = @Server(url ="http://35.202.168.112:80/v1") //TODO: Uncomment this line for production release
        servers = @Server(url ="http://localhost:80/v1")
)
public class ChallengeApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(AdDataResource.class);
        classes.add(CampaignDataResource.class);
        classes.add(ImpressionDataResource.class);
        classes.add(InteractionTypeDataResource.class);
        classes.add(UserDataResource.class);
        classes.add(ReportCampaignResource.class);
        classes.add(ReportAdResource.class);
        classes.add(GenerateDataResource.class);
        return classes;
    }
}
