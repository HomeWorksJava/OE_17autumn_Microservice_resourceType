
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.microservices.homework.resourcetype.datamodel.*;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.helpers.ListLinkSerializer;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.resources.ResourceTypeController;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

public class ResourceTypes {
    
    @JsonIgnore
    private int limit;
    @JsonIgnore
    private int page;
    @JsonIgnore
    private int offset;
    @JsonIgnore
    private int fullitemnumber;
    
    private List<ResourceType> resourcetypes = new ArrayList();

    @InjectLinks({        
            @InjectLink(
                    resource = ResourceTypeController.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "GetResourceTypesWithPage",
                    condition = "${instance.page > 1}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset - instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "prev"
            ), @InjectLink(
                    resource = ResourceTypeController.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "GetResourceTypesWithPage",
                    condition = "${instance.offset + instance.limit < instance.fullitemnumber}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "next"
            )        
    })
    @JsonSerialize(using = ListLinkSerializer.class)  
    private List<Link> links;
      
    public ResourceTypes() {
    }

    public ResourceTypes(int pageitemnumber, int startitemnumber, int fullitemnumber) {
        this.limit = pageitemnumber;
        this.offset = startitemnumber;
        this.fullitemnumber = fullitemnumber;
        this.page = this.offset/pageitemnumber +1;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
 
    public void addResource(ResourceType resource) {
        this.getResourcetypes().add(resource);
    }
    
    public void addResources(List<ResourceType> resources) {
        this.resourcetypes = resources;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getFullitemnumber() {
        return fullitemnumber;
    }

    public void setFullitemnumber(int fullitemnumber) {
        this.fullitemnumber = fullitemnumber;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<ResourceType> getResourcetypes() {
        return resourcetypes;
    }

    public void setResourcetypes(List<ResourceType> resourcetypes) {
        this.resourcetypes = resourcetypes;
    }
}