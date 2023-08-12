package be.he2b.scrum.web;

import java.util.Collection;

import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import be.he2b.scrum.DTOS.ProjectDTO;
import be.he2b.scrum.business.ScrumService;
import be.he2b.scrum.model.Project;
import be.he2b.scrum.model.Story;

@Controller
public class ProjectController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @Autowired
    ScrumService scrumService;

    @GetMapping("/projects")
    public String showProjects(Model model) {
        model.addAttribute("projects", scrumService.getAllProjects());
        return "projects";
    }

    @GetMapping("/projects/project/{projectName}")
    public String showProjectDetail(@PathVariable(name = "projectName") String projectName, Model model) {
        Collection<ProjectDTO> projects = scrumService.getProjectsStartingWith(projectName);
        Collection<Story> stories = scrumService.findBySprintProjectName(projectName);
        model.addAttribute("projects", projects);
        model.addAttribute("stories", stories);
        return "projectDetails";
    }

    @PostMapping("/addStoryy")
    private String saveStory(@RequestBody Story story, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // Les 2 lignes suivantes sont pcq il faut redonner la liste des projets
            // car on recharge la page
            Collection<Project> projects = scrumService.getAllProjects();
            model.addAttribute("projects", projects);
            return "projects";
        }
        scrumService.saveOrUpdate(story);
        return "redirect:/projects";
    }
}
