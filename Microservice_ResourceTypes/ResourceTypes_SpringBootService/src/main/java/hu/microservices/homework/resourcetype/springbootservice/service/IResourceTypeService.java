package hu.microservices.homework.resourcetype.springbootservice.service;

import hu.microservices.homework.resourcetype.springdatamodel.EMaterial;
import hu.microservices.homework.resourcetype.springdatamodel.EMeasurement;
import hu.microservices.homework.resourcetype.springdatamodel.ResourceType;
import hu.microservices.homework.resourcetype.springdatamodel.ResourceTypePager;

import java.util.List;

public interface IResourceTypeService {
    List<ResourceType> getAllResourceTypes();
    ResourceTypePager getPagedResourceTypes(int offset, int limit);
    ResourceType getResourceType(long id);

    boolean addResourceType(String name, EMeasurement measurement, EMaterial material, String description);

    boolean deleteResourceType(long id);
}
