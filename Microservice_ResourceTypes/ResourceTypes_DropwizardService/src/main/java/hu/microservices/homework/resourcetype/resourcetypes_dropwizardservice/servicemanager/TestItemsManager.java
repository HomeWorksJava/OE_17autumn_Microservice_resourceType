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

    //null: nincs erőforrás
    public List<ResourceType> getResourceTypeTestItems() {
        return this.resourceTypeTestItems.size() > 0 ? this.resourceTypeTestItems : null;
    }

    //null: nincs erőforrás
    public ResourceTypes getResourceTypeTestItems(int offset, int limit) {
        ResourceTypes resourceTypesTemp = new ResourceTypes(limit, offset, resourceTypeTestItems.size());
        for (int i = 0; (i + offset) < resourceTypeTestItems.size() && i < limit; i++) {
            resourceTypesTemp.addResource(resourceTypeTestItems.get(i + offset));
        }
        return resourceTypesTemp.getResourcetypes().isEmpty() ? null : resourceTypesTemp;
    }

    //null: nincs erőforrás
    public ResourceType getResourceTypeTestItem(long id) {
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {
                return resourceType;
            }
        }
        return null;
    }

    //null param: default ertek
    //return false: nincs nev
    public Boolean addResourceType(String name, String description, String measurement, String material) {

        if (name == null) {
            return false;
        }
        String newResourceTypeName = name;

        String newResourceTypeDescription;
        if (description == null) {
            newResourceTypeDescription = "";
        } else {
            newResourceTypeDescription = description;
        }

        EMeasurement newResourceTypeMeasurement;
        if (measurement == null) {
            newResourceTypeMeasurement = EMeasurement.G;
        } else {
            newResourceTypeMeasurement = stringMeasurementToEnum(measurement);
        }

        EMaterial newResourceTypeMaterial;
        if (material == null) {
            newResourceTypeMaterial = EMaterial.SZERVES;
        } else {
            newResourceTypeMaterial = stringMaterialToEnum(material);
        }

        this.resourceTypeTestItems.add(new ResourceType(getNextId(), newResourceTypeName, newResourceTypeMeasurement, newResourceTypeMaterial, newResourceTypeDescription));
        return true;
    }

    //null: nincs módosítás
    //return false: nincs id
    //return null: nem található az erőforrás
    public Boolean modifyResourceType(Long id, String name, String description, String measurement, String material) {
        if (id == null) {
            return false;
        }
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {

                if (name != null) {
                    resourceType.setName(name);
                }

                if (description != null) {
                    resourceType.setDescription(description);
                }

                if (measurement != null) {
                    resourceType.setMeasurement(stringMeasurementToEnum(measurement));
                }

                if (material != null) {
                    resourceType.setMaterial(stringMaterialToEnum(material));
                }

                return true;
            }
        }
        return null;
    }

    //return false: nincs id
    //return null: nem található az erőforrás
    public Boolean deleteResourceType(Long id) {
        if (id == null) {
            return false;
        }
        for (ResourceType resourceType : resourceTypeTestItems) {
            if (resourceType.getId() == id) {
                resourceTypeTestItems.remove(resourceType);
                return true;
            }
        }
        return null;
    }

    private EMaterial stringMaterialToEnum(String material) {
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
        return ematerial;
    }

    private EMeasurement stringMeasurementToEnum(String measurement) {
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
        return emeasurement;
    }
}
