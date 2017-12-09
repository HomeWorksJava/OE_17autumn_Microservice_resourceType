/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.servicemanager;

import hu.microservices.homework.resourcetype.datamodel.EMaterial;
import hu.microservices.homework.resourcetype.datamodel.EMeasurement;
import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.datamodel.ResourceTypes;
import hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.dbmanager.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver
 */
public class ServiceManager {

    DBManager dbmanager;

    public ServiceManager() {
        try {
            dbmanager = new DBManager();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<ResourceType> getResourceTypeItems() {
        List<ResourceType> resourceTypeItems = new ArrayList<ResourceType>();
        try {
            ResultSet result = dbmanager.ExecuteQuery("select * from resourcetypes");
            while (result.next()) {
                resourceTypeItems.add(new ResourceType(result.getInt("id"),
                        result.getString("name"),
                        stringMeasurementToEnum(result.getString("measurement")),
                        stringMaterialToEnum(result.getString("material")),
                        result.getString("description")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            dbmanager.Close();
            return null;
        }
        dbmanager.Close();
        return resourceTypeItems.size() > 0 ? resourceTypeItems : null;
    }

    public ResourceTypes getResourceTypeItems(int offset, int limit) {
        List<ResourceType> resourceTypeTestItems = getResourceTypeItems();
        ResourceTypes resourceTypesTemp = new ResourceTypes(limit, offset, resourceTypeTestItems.size());
        for (int i = 0; (i + offset) < resourceTypeTestItems.size() && i < limit; i++) {
            resourceTypesTemp.addResource(resourceTypeTestItems.get(i + offset));
        }
        return resourceTypesTemp.getResourcetypes().isEmpty() ? null : resourceTypesTemp;
    }

    public ResourceType getResourceTypeItem(long id) {
        ResourceType resourceTypeItem = null;
        try {
            ResultSet result = dbmanager.ExecuteQuery("select * from resourcetypes where id = " + id);
            while (result.next()) {
                resourceTypeItem = new ResourceType(result.getInt("id"),
                        result.getString("name"),
                        stringMeasurementToEnum(result.getString("measurement")),
                        stringMaterialToEnum(result.getString("material")),
                        result.getString("description")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            dbmanager.Close();
            return null;
        }
        dbmanager.Close();
        return resourceTypeItem == null ? null : resourceTypeItem;
    }

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

        try {
            dbmanager.ExecuteUpdate("insert into resourcetypes (id, name, measurement, material, description) values (default, "
                    + "'" + newResourceTypeName + "'" + ", "
                    + "'" + newResourceTypeMeasurement + "'" + ", "
                    + "'" + newResourceTypeMaterial + "'" + ", "
                    + "'" + newResourceTypeDescription + "'" + ")");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            dbmanager.Close();
            return false;
        }
        dbmanager.Close();
        return true;
    }

    public Boolean modifyResourceType(Long id, String name, String description, String measurement, String material) {
        if (id == null) {
            return false;
        }
        ResourceType resourceType = getResourceTypeItem(id);
        if (resourceType == null) {
            return false;
        } else {
            if (name != null) {
                try {
                    dbmanager.ExecuteUpdate("update resourcetypes set name = " + "'" + name + "'" + " where id = " + id);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (description != null) {
                try {
                    dbmanager.ExecuteUpdate("update resourcetypes set description = " + "'" + description + "'" + " where id = " + id);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (measurement != null) {
                try {
                    dbmanager.ExecuteUpdate("update resourcetypes set measurement = " + "'" + measurement + "'" + " where id = " + id);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (material != null) {
                try {
                    dbmanager.ExecuteUpdate("update resourcetypes set material = " + "'" + material + "'" + " where id = " + id);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dbmanager.Close();
            return true;
        }
    }

    public Boolean deleteResourceType(Long id) {
       if (id == null) {
            return false;
        }
        ResourceType resourceType = getResourceTypeItem(id);
        if(resourceType == null) {
            return null;
        } else {
            try {
                    dbmanager.ExecuteUpdate("delete from resourcetypes where id = " + id);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
                    dbmanager.Close();
                }
            dbmanager.Close();
            return true;
        }

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
