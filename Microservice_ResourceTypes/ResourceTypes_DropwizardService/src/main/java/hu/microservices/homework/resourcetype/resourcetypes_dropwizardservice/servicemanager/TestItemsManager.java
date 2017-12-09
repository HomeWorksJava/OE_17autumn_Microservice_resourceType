package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.servicemanager;

import hu.microservices.homework.resourcetype.datamodel.*;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.datamodel.ResourceTypes;
import java.util.ArrayList;
import java.util.List;

public class TestItemsManager {

    private List<ResourceType> resourceTypeTestItems;
    private long nextId = 0;

    public TestItemsManager() {
        this.resourceTypeTestItems = new ArrayList<>();
        resourceTypeTestItems.add(new ResourceType(getNextId(), "réz", EMeasurement.G, EMaterial.SZERVETLEN, "Fém érc, kis értékű."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "vas", EMeasurement.G, EMaterial.SZERVETLEN, "Fém, fegyver és építőanyag alapanyag."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "arany", EMeasurement.G, EMaterial.SZERVETLEN, "Nemesfém, nagy értékű."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "ezüst", EMeasurement.G, EMaterial.SZERVETLEN, "Nemesfém, mérsékelten nagy értékű."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "fa", EMeasurement.G, EMaterial.SZERVES, "Alapvető építőanyag."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "búza", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "napraforgó", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "árpa", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "sör", EMeasurement.L, EMaterial.SZERVES, "Ki ne szeretné?"));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "szőlő", EMeasurement.G, EMaterial.SZERVES, "Friss gyümölcs mindig jó."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "bor", EMeasurement.L, EMaterial.SZERVES, "Amire minden szőlő született..."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "tehén", EMeasurement.DB, EMaterial.SZERVES, "Boooooo"));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "birka", EMeasurement.DB, EMaterial.SZERVES, "Meeeee"));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "csirke", EMeasurement.DB, EMaterial.SZERVES, "Cirke, nem mackaaa."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "sertés", EMeasurement.DB, EMaterial.SZERVES, "Nincs mit részletezni rajta, fincsi!"));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "gyapot", EMeasurement.G, EMaterial.SZERVES, "Mit szednek a négerek? Gyapot!"));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "gyapjú", EMeasurement.G, EMaterial.SZERVES, "Textil alapanyag."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "textil", EMeasurement.M, EMaterial.SZERVES, "Ruha alapanyag."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "selyem", EMeasurement.M, EMaterial.SZERVES, "Előkelő ruha alapanyag."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "víz", EMeasurement.L, EMaterial.SZERVETLEN, "Amit a lovak isznak."));
        resourceTypeTestItems.add(new ResourceType(getNextId(), "tej", EMeasurement.L, EMaterial.SZERVES, "Amit a tehenek isznak."));
    }

    public long getNextId() {
        return ++nextId;
    }

    public List<ResourceType> getResourceTypeItems() {
        return this.resourceTypeTestItems;
    }

    public ResourceTypes getResourceTypeItems(int offset, int limit) {
        ResourceTypes resourceTypesTemp = new ResourceTypes(limit, offset, resourceTypeTestItems.size());
        for (int i = 0; (i + offset) < resourceTypeTestItems.size() && i < limit; i++) {
            resourceTypesTemp.addResource(resourceTypeTestItems.get(i + offset));
        }
        return resourceTypesTemp.getResourcetypes().isEmpty() ? null : resourceTypesTemp;
    }

    public ResourceType getResourceTypeItem(long id) {
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {
                return resourceType;
            }
        }
        return null;
    }

    public boolean addResourceType(String name, String description, String measurement, String material) {

        EMeasurement emeasurement;

        switch (measurement) {
            case "G":
                emeasurement = EMeasurement.G;
                break;
            case "L":
                emeasurement = EMeasurement.L;
                break;
            case "M":
                emeasurement = EMeasurement.M;
                break;
            case "DB":
                emeasurement = EMeasurement.DB;
                break;
            case "M2":
                emeasurement = EMeasurement.M2;
                break;
            case "M3":
                emeasurement = EMeasurement.M3;
                break;
            default:
                emeasurement = EMeasurement.G;
                break;
        }

        EMaterial ematerial;

        switch (material) {
            case "SZERVES":
                ematerial = EMaterial.SZERVES;
                break;
            case "SZERVETLEN":
                ematerial = EMaterial.SZERVETLEN;
                break;
            default:
                ematerial = EMaterial.SZERVES;
                break;
        }
        this.resourceTypeTestItems.add(new ResourceType(getNextId(), name, emeasurement, ematerial, description));
        return true;
    }

    public boolean modifyResourceType(long id, String name, String description, String measurement, String material) {
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {
                if (!name.equals("")) {
                    resourceType.setName(name);
                }
                if (!description.equals("")) {
                    resourceType.setDescription(description);
                }
                if (!measurement.equals("")) {
                    EMeasurement emeasurement;

                    switch (measurement) {
                        case "G":
                            emeasurement = EMeasurement.G;
                            break;
                        case "L":
                            emeasurement = EMeasurement.L;
                            break;
                        case "M":
                            emeasurement = EMeasurement.M;
                            break;
                        case "DB":
                            emeasurement = EMeasurement.DB;
                            break;
                        case "M2":
                            emeasurement = EMeasurement.M2;
                            break;
                        case "M3":
                            emeasurement = EMeasurement.M3;
                            break;
                        default:
                            emeasurement = EMeasurement.G;
                            break;
                    }
                    resourceType.setMeasurement(emeasurement);
                }
                if (!material.equals("")) {
                    EMaterial ematerial;

                    switch (material) {
                        case "SZERVES":
                            ematerial = EMaterial.SZERVES;
                            break;
                        case "SZERVETLEN":
                            ematerial = EMaterial.SZERVETLEN;
                            break;
                        default:
                            ematerial = EMaterial.SZERVES;
                            break;
                    }

                    resourceType.setMaterial(ematerial);
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteResourceType(long id) {
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {
                resourceTypeTestItems.remove(resourceType);
                return true;
            }
        }
        return false;
    }

}
