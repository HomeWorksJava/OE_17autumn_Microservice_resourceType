package hu.microservices.homework.resourcetype.springbootservice.controller;

import hu.microservices.homework.resourcetype.datamodel.EMaterial;
import hu.microservices.homework.resourcetype.datamodel.EMeasurement;
import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.datamodel.ResourceTypePager;
import hu.microservices.homework.resourcetype.springbootservice.service.IResourceTypeService;
import hu.microservices.homework.resourcetype.springbootservice.service.ResourceTypePageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/resourcetype")
public class ResourceTypeController {

    @Autowired
    private IResourceTypeService resourceTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String resourceTypeWelcome() {
        return "Resource Type service works!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllResourceTypes() {
        return new ResponseEntity<>(resourceTypeService.getAllResourceTypes(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getResourceType(@PathVariable("id") String id) {
        long resourceTypeId;
        try {
            resourceTypeId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ResourceType result = resourceTypeService.getResourceType(resourceTypeId);
        if (result != null) {
            return new ResponseEntity<>(resourceTypeService.getResourceType(resourceTypeId), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{offset}/{limit}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getResourceType(
            @PathVariable("offset") String offset,
            @PathVariable("limit") String limit) {

        int off = 0, lim = 0;
        ResourceTypePager result;
        try {
            off = Integer.valueOf(offset);
            lim = Integer.valueOf(limit);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("BAD REQUEST! Offset and limit parameters must be numbers!", HttpStatus.BAD_REQUEST);
        }
        try {
            result = resourceTypeService.getPagedResourceTypes(off, lim);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("BAD REQUEST! Provided offset cause index out of bounds exception!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> postResourceType(
            @RequestParam("name") String name,
            @RequestParam("measurement") EMeasurement measurement,
            @RequestParam("material") EMaterial material,
            @RequestParam("description") String description) {

        resourceTypeService.addResourceType(name, measurement, material, description);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
