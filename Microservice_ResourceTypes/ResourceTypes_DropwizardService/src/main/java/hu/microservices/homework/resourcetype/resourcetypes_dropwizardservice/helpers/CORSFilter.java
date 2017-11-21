
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.helpers;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {
    
    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext response) throws IOException {
      response.getHeaders().add("Access-Control-Allow-Origin", "*");
      response.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      response.getHeaders().add("Access-Control-Allow-Credentials", "true");
      response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
      response.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
