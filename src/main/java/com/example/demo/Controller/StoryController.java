package com.example.demo.Controller;

import com.example.demo.Entity.Project;
import com.example.demo.Service.StoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.*;

@RestController
@RequestMapping("v1/stories/{id}")
public class StoryController {

    private StoryService storyServiceImpl;

    public RestTemplate restTemplate = new RestTemplate();

    @PostMapping()
    public ResponseEntity saveStory(@PathVariable("id") int projectId) throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Project> project = restTemplate.exchange("http://projects.cfapps.io/v1/project/"+projectId, GET,new HttpEntity<>(httpHeaders),Project.class);

        if(project.getBody().isActive() && project.getStatusCode().is2xxSuccessful()){
            storyServiceImpl.save(project.getBody());
            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity getStoryById(@PathVariable("id") int projectId){

      return storyServiceImpl.getStoryById(projectId);


    }
}
