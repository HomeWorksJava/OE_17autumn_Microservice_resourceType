package hu.microservices.homework.resourcetype.springbootservice.controller;

import hu.microservices.homework.resourcetype.datamodel.ResourceType;
import hu.microservices.homework.resourcetype.springbootservice.service.IResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "/**")
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
            
}
