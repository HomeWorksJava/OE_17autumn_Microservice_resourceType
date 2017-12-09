
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice;

import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.dbmanager.DBManager;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.helpers.CORSFilter;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.resources.ResourceTypeController;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.servicemanager.ServiceManager;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.List;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

public class ResourceTypes_DropwizardServiceApplication extends Application<ResourceTypes_DropwizardServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ResourceTypes_DropwizardServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ResourceTypes_DropwizardService";
    }

    @Override
    public void initialize(final Bootstrap<ResourceTypes_DropwizardServiceConfiguration> bootstrap) {
        
    }

    @Override
    public void run(final ResourceTypes_DropwizardServiceConfiguration configuration,
                    final Environment environment) {
        
        environment.jersey().getResourceConfig().packages(getClass().getPackage().getName()).register(DeclarativeLinkingFeature.class);
        environment.jersey().register(new CORSFilter());  
        environment.jersey().register(new ResourceTypeController());
 
    }
}
