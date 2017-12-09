package hu.microservices.homework.resourcetype.springdatamodel;

public class ResourceType {

    private long id;
    private String name;
    private EMeasurement measurement;
    private EMaterial material;
    private String description;

    public ResourceType() {
    }

    public ResourceType(long id, String name, EMeasurement measurement, EMaterial material, String description) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.material = material;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EMeasurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(EMeasurement measurement) {
        this.measurement = measurement;
    }

    public EMaterial getMaterial() {
        return material;
    }

    public void setMaterial(EMaterial material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
