package com.edmtz.controller;

import com.edmtz.model.Example;
import com.edmtz.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private ExampleRepository exampleRepository;

    @GetMapping("/populate")
    public String populateData(){
        Example entity1 = new Example();
        entity1.setName("Eduardo GMTZ");
        entity1.setAge(25);

        Example entity2 = new Example();
        entity2.setName("Eduardo dddd");
        entity2.setAge(23);

        exampleRepository.save(entity1);
        exampleRepository.save(entity2);

        return "Populated!";
    }
}
