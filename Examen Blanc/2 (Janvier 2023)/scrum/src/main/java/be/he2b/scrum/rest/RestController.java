package be.he2b.scrum.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.he2b.scrum.DTOS.ProjectDTO;
import be.he2b.scrum.business.ScrumService;
import be.he2b.scrum.model.Project;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RestController {

    @Autowired
    ScrumService project;

    @GetMapping("/projectsListStartingWith/{projectNamePrefix}")
    public ResponseEntity<Collection<ProjectDTO>> projectsList(
            @PathVariable(value = "projectNamePrefix") String projectNamePrefix) {
        return new ResponseEntity<>(project.getProjectsStartingWith(projectNamePrefix), HttpStatus.OK);
    }

    @GetMapping("/allProjects")
    public ResponseEntity<Collection<Project>> allProjectsList() {
        return new ResponseEntity<>(project.getAllProjects(), HttpStatus.OK);
    }
}