package hu.microservices.homework.resourcetype.springbootservice.service;

import hu.microservices.homework.resourcetype.datamodel.EMaterial;
import hu.microservices.homework.resourcetype.datamodel.EMeasurement;
import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.datamodel.ResourceTypePager;

import java.util.List;

public interface IResourceTypeService {
    List<ResourceType> getAllResourceTypes();
    ResourceTypePager getPagedResourceTypes(int offset, int limit);
    ResourceType getResourceType(long id);

    boolean addResourceType(String name, EMeasurement measurement, EMaterial material, String description);
}
