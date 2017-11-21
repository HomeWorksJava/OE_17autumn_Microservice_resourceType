
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.resources;

import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.datamodel.ResourceTypes;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.servicemanager.TestItemsManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resourcetype")
@Produces({ MediaType.APPLICATION_JSON+";charset=UTF-8"})
public class ResourceTypeController {
    
    @GET
    public Response welcome() {
        return Response.ok("Welcome To Resource Type Service!").build();
    }
    
    TestItemsManager testItemsManager = new TestItemsManager();
    
    @GET
    @Path("/{which_one}")
    public Response GetResourceTypes(@PathParam("which_one") String which_one) {
        if(which_one.equals("all")) {
            return testItemsManager.getResourceTypeTestItems() == null ?
                    Response.status(Response.Status.NO_CONTENT).build():
                    Response.ok(testItemsManager.getResourceTypeTestItems()).build();
        } else {
            long id = Long.parseLong(which_one);
            return testItemsManager.getResourceTypeTestItem(id) == null ?
                    Response.status(Response.Status.NO_CONTENT).build():
                    Response.ok(testItemsManager.getResourceTypeTestItem(id)).build();
        }
    }
        
    @GET
    @Path("/{offset}/{limit}") 
    public Response GetResourceTypes(@PathParam("offset") int offset, @PathParam("limit") int limit) {
        ResourceTypes resourceTypesTemp = testItemsManager.getResourceTypeTestItems(offset, limit);
        return resourceTypesTemp == null ?
                Response.status(Response.Status.NO_CONTENT).build(): 
                Response.ok(resourceTypesTemp).build();     
    }
    
    @POST
    public Response AddResourceType(@QueryParam("name") String name,
                                 @QueryParam("description") String description,
                                 @QueryParam("measurement") String measurement,
                                 @QueryParam("material") String material) {
        
        Boolean success = testItemsManager.addResourceType(name, description, measurement, material);

        if(success == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();  
    }
    
    @PUT
    @Path("/{id}") 
    public Response ModifyResourceType(@PathParam("id") long id,
                                 @QueryParam("name") String name,
                                 @QueryParam("description") String description,
                                 @QueryParam("measurement") String measurement,
                                 @QueryParam("material") String material) {
        
       Boolean success = testItemsManager.modifyResourceType(Long.valueOf(id), name, description, measurement, material);
        
        if(success == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        if(success == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response DeleteResourceType(@PathParam("id") long id) {
        
       Boolean success = testItemsManager.deleteResourceType(Long.valueOf(id));
        
        if(success == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        if(success == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
