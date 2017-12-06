package hu.microservices.homework.resourcetype.springbootservice.service;

import hu.microservices.homework.resourcetype.springdatamodel.ResourceType;
import hu.microservices.homework.resourcetype.springdatamodel.ResourceTypePager;

import java.util.List;

public class ResourceTypePageBuilder {

    public static ResourceTypePager createResourcePage(List<ResourceType> allResources, int offset, int limit) throws IndexOutOfBoundsException {
        ResourceTypePager result = new ResourceTypePager();
        result.setMaxItems(allResources.size());

        if (offset < result.getMaxItems() && limit >= 0) {
            result.setLimit(limit);
            result.setItems(allResources.subList(offset, Math.min(offset + limit, result.getMaxItems())));
            result.setOffset(offset);
            result.setLimit(limit);

            // links:
            int prevOffset = Math.max(offset - limit, 0);
            int nextOffset = Math.min(offset + limit, result.getMaxItems());
            result.setPreviousLink("/resourcetype/" + prevOffset + "/" + limit);
            result.setNextLink("/resourcetype/" + nextOffset + "/" + limit);
        } else {
            //
            throw new IndexOutOfBoundsException("Offset is larger than the the size of the ResourceType list.");
        }
        result.setOffset(offset);

        return result;
    }

}