package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final ResourceTypes_DropwizardServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
