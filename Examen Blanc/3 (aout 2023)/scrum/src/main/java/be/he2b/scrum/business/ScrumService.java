package be.he2b.scrum.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.he2b.scrum.DTO.ProjectInfos;
import be.he2b.scrum.model.Project;
import be.he2b.scrum.model.Sprint;
import be.he2b.scrum.model.Story;
import be.he2b.scrum.repository.ProjectRepository;
import be.he2b.scrum.repository.SprintRepository;
import be.he2b.scrum.repository.StoryRepository;

@Service
public class ScrumService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    StoryRepository storyRepository;

    public Collection<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Collection<ProjectInfos> getAllProjectsInfos(String projectNamePrefix) {
        return projectRepository.numberSprintsAndStoriesInAllProjects(projectNamePrefix);
    }

    public Project getProject(String projectName) {
        return projectRepository.findByName(projectName);
    }

    public Collection<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Collection<Sprint> getSprints(Project project) {
        return sprintRepository.findByProject(project);
    }

    public Sprint addSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public Collection<Story> getStories(Sprint sprint) {
        return storyRepository.findBySprint(sprint);
    }

    public Story addStory(Story story) {
        return storyRepository.save(story);
    }
}
