package be.he2b.scrum.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.he2b.scrum.business.ScrumService;
import be.he2b.scrum.model.Project;
import be.he2b.scrum.model.Sprint;
import be.he2b.scrum.model.Story;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

    @Autowired
    private ScrumService scrumService;

    // @Autowired
    // private List<String> errors = new ArrayList<>();

    // public ProjectController() {
    // }

    @GetMapping("/private")
    public String privé() {
        return "private";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("projectsList", scrumService.getAllProjects());
        return "home";
    }

    @GetMapping("/projects/{projectName}/details")
    public String projectDetails(Model model, @PathVariable("projectName") String projectName) {
        if (scrumService.getProject(projectName) == null) {
            model.addAttribute("errorMessage", "Le projet demandé n'existe pas.");
            return "error";
        }
        model.addAttribute("projectsInfosList", scrumService.getAllProjectsInfos(projectName));
        model.addAttribute("project", scrumService.getProject(projectName));
        Project project = scrumService.getProject(projectName);
        Collection<Sprint> sprints = scrumService.getSprints(project);
        Collection<Story> stories = new ArrayList<>();
        for (Sprint sprint : sprints) {
            stories.addAll(scrumService.getStories(sprint));
        }
        model.addAttribute("stories", stories);
        return "projectDetails";
    }

    // Gère la création du formulaire
    @GetMapping("projects/{projectName}/details/newStory")
    public String getNewStoryForm(Model model, @PathVariable("projectName") String projectName) {
        Project project = scrumService.getProject(projectName);
        model.addAttribute("project", project);

        Story story = new Story();

        model.addAttribute("story", story);
        return "projectNewStory";
    }

    // Gère la soumission du formulaire
    @PostMapping("projects/{projectName}/details/newStory")
    public String handleNewStoryForm(Model model, @PathVariable("projectName") String projectName, @Valid Story story,
            Errors errors, RedirectAttributes redirectAttrs) {

        if (errors.hasErrors()) {
            System.out.println("errorssss");
            model.addAttribute("project", scrumService.getProject(projectName));
            model.addAttribute("errorMsg", errors);
            return "projectNewStory";
        } else {

            redirectAttrs.addAttribute("projectName", projectName);
            Project project = scrumService.getProject(projectName);
            model.addAttribute("project", project);

            List<Sprint> sprints = new ArrayList<>();
            for (Sprint sprint : scrumService.getSprints(project)) {
                sprints.add(sprint);
            }

            Sprint lastSprint = sprints.get(sprints.size() - 1);
            Sprint sprint = new Sprint();
            sprint.setNumber(lastSprint.getNumber() + 1);
            sprint.setProject(project);
            sprint.setDays(lastSprint.getDays());
            scrumService.addSprint(sprint);

            story.setSprint(sprint);
            scrumService.addStory(story);
        }
        return "redirect:/projects/{projectName}/details";
    }

    // @GetMapping("/request/error")
    // public String badRequest(Model model) {
    // model.addAttribute("errors", errors);
    // return "error";
    // }
}
