package hu.microservices.homework.resourcetype.springbootservice.controller;

import hu.microservices.homework.resourcetype.springbootservice.service.IResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "/**")
@Controller
public class ResourceTypeController {
    
    @Autowired
    private IResourceTypeService resourceTypeService;

    @RequestMapping("/")
    @ResponseBody
    public String resourceTypeWelcome() {
        return "Resource Type service works!";
    }
    
    @RequestMapping("/getresourcetypes/all")
    @ResponseBody
    public ResponseEntity<Object> getAllResourceTypes() {        
        return new ResponseEntity<>(resourceTypeService.getAllResourceTypes(), HttpStatus.OK);
    }
            
}
