package hu.microservices.homework.resourcetype.springbootservice.service;

import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import java.util.List;

public interface IResourceTypeService {
    List<ResourceType> getAllResourceTypes();
    List<ResourceType> getPagedResourceTypes(int offset, int limit);
    ResourceType getResourceType(long id);
    
    
}
