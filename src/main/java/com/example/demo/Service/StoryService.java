package com.example.demo.Service;


import com.example.demo.Entity.Project;
import org.springframework.http.ResponseEntity;

public interface StoryService {

    public void save(Project Project);

    public ResponseEntity getStoryById(int projectId);

}
