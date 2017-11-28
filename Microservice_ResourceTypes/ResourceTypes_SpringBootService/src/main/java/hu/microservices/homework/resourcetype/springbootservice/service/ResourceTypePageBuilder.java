package hu.microservices.homework.resourcetype.springbootservice.service;

import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.datamodel.ResourceTypePager;

import java.util.List;

public class ResourceTypePageBuilder {

    public static ResourceTypePager createResourcePage(List<ResourceType> allResources, int offset, int limit) {
        ResourceTypePager result = new ResourceTypePager();
        result.setMaxItems(allResources.size());

        if (offset < result.getMaxItems()) {
            result.setLimit(limit);
            result.setItems(allResources.subList(offset, Math.min(offset + limit, result.getMaxItems())));
            result.setOffset(offset);
            result.setLimit(limit);
//            if (offset > 0) {
//                result.setPreviousLink();
//            }
//            result.setNextLink();
        } else {
            //
            throw new IndexOutOfBoundsException("Offset is larger than the the size of the ResourceType list.");
        }
        result.setOffset(offset);

        return result;
    }

}