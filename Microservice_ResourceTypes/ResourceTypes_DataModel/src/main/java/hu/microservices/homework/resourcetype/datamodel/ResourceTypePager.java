package hu.microservices.homework.resourcetype.datamodel;

import java.util.List;

public class ResourceTypePager {

    private List<ResourceType> items;
    private int offset;
    private int limit;
    private int maxItems;
    private String prevoiusLink;
    private String nextLink;

    public ResourceTypePager() {
    }

    public List<ResourceType> getItems() {
        return items;
    }

    public void setItems(List<ResourceType> items) {
        this.items = items;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public String getPrevoiusLink() {
        return prevoiusLink;
    }

    public void setPreviousLink(String prevoiusLink) {
        this.prevoiusLink = prevoiusLink;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }
}
