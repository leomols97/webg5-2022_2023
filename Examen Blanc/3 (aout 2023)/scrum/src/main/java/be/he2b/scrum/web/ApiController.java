package be.he2b.scrum.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.he2b.scrum.DTO.ProjectInfos;
import be.he2b.scrum.business.ScrumService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ScrumService scrumService;

    @GetMapping("/projects/get")
    public ResponseEntity<?> getProjectsStartingWith(@RequestParam("projectNamePrefix") String projectNamePrefix) {
        try {
            Collection<ProjectInfos> projects = scrumService.getAllProjectsInfos(projectNamePrefix);
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite : " + e.getMessage());
        }
    }
}
