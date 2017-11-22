package hu.microservices.homework.resourcetype.springbootservice.service;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.datamodel.EMaterial;
import hu.microservices.homework.resourcetype.datamodel.EMeasurement;

@Component
public class ResourceTypeServiceMemoryImplementation implements IResourceTypeService {
    
    //@Value("${name:Resource types}")
    // private String name;
    private final List<ResourceType> resourceTypes;
    private long nextId;

    private long getNextId() {
        return nextId++;
    }

    public ResourceTypeServiceMemoryImplementation() {
        resourceTypes = new ArrayList<>();

        resourceTypes.add(new ResourceType(1, "réz", EMeasurement.G, EMaterial.SZERVETLEN, "Fém érc, kis értékű."));
        resourceTypes.add(new ResourceType(2, "vas", EMeasurement.G, EMaterial.SZERVETLEN, "Fém, fegyver és építőanyag alapanyag."));
        resourceTypes.add(new ResourceType(3, "arany", EMeasurement.G, EMaterial.SZERVETLEN, "Nemesfém, nagy értékű."));
        resourceTypes.add(new ResourceType(4, "ezüst", EMeasurement.G, EMaterial.SZERVETLEN, "Nemesfém, mérsékelten nagy értékű."));
        resourceTypes.add(new ResourceType(5, "fa", EMeasurement.G, EMaterial.SZERVES, "Alapvető építőanyag."));
        resourceTypes.add(new ResourceType(6, "búza", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypes.add(new ResourceType(7, "napraforgó", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypes.add(new ResourceType(8, "árpa", EMeasurement.G, EMaterial.SZERVES, "Alapvető élelmiszer."));
        resourceTypes.add(new ResourceType(9, "sör", EMeasurement.L, EMaterial.SZERVES, "Ki ne szeretné?"));
        resourceTypes.add(new ResourceType(10, "szőlő", EMeasurement.G, EMaterial.SZERVES, "Friss gyümölcs mindig jó."));
        resourceTypes.add(new ResourceType(11, "bor", EMeasurement.L, EMaterial.SZERVES, "Amire minden szőlő született..."));
        resourceTypes.add(new ResourceType(12, "tehén", EMeasurement.DB, EMaterial.SZERVES, "Boooooo"));
        resourceTypes.add(new ResourceType(13, "birka", EMeasurement.DB, EMaterial.SZERVES, "Meeeee"));
        resourceTypes.add(new ResourceType(14, "csirke", EMeasurement.DB, EMaterial.SZERVES, "Cirke, nem mackaaa."));
        resourceTypes.add(new ResourceType(15, "sertés", EMeasurement.DB, EMaterial.SZERVES, "Nincs mit részletezni rajta, fincsi!"));
        resourceTypes.add(new ResourceType(16, "gyapot", EMeasurement.G, EMaterial.SZERVES, "Mit szednek a négerek? Gyapot!"));
        resourceTypes.add(new ResourceType(17, "gyapjú", EMeasurement.G, EMaterial.SZERVES, "Textil alapanyag."));
        resourceTypes.add(new ResourceType(18, "textil", EMeasurement.M, EMaterial.SZERVES, "Ruha alapanyag."));
        resourceTypes.add(new ResourceType(19, "selyem", EMeasurement.M, EMaterial.SZERVES, "Előkelő ruha alapanyag."));
        resourceTypes.add(new ResourceType(20, "víz", EMeasurement.L, EMaterial.SZERVETLEN, "Amit a lovak isznak."));
        resourceTypes.add(new ResourceType(21, "tej", EMeasurement.L, EMaterial.SZERVES, "Amit a tehenek isznak."));

        nextId = 22;
    }

    @Override
    public List<ResourceType> getAllResourceTypes() {
        return resourceTypes;
    }

    @Override
    public List<ResourceType> getPagedResourceTypes(int offset, int limit) {
        return resourceTypes;
    }

    @Override
    public ResourceType getResourceType(long id) {
        for (ResourceType resourceType: resourceTypes) {
            if (resourceType.getId() == id) {
                return resourceType;
            }
        }
        return null;
    }

    public boolean deleteResource(long id) {
        ResourceType res = getResourceType(id);
        if (res != null) {
            resourceTypes.remove(res);
            return true;
        }
        return false;
    }

    public ResourceType postResourceType(String name, EMeasurement measurement, EMaterial material, String description) {
        ResourceType resourceType = new ResourceType(getNextId(), name, measurement, material, description);
        resourceTypes.add(resourceType);
        return resourceType;
    }
}
