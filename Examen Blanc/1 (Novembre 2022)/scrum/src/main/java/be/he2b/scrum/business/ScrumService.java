package be.he2b.scrum.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.he2b.scrum.DTOS.ProjectDTO;
import be.he2b.scrum.model.Project;
import be.he2b.scrum.model.Story;
import be.he2b.scrum.repository.ProjectRepository;
import be.he2b.scrum.repository.StoryRepository;

@Service
public class ScrumService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Collection<ProjectDTO> getProjectsStartingWith(String projectNamePrefix) {
        return storyRepository.findProjectWithNameBegin(projectNamePrefix);
    }

    public Collection<Story> findBySprintProjectName(String projectName) {
        return storyRepository.findBySprintProjectName(projectName);
    }

    public Collection<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void saveOrUpdate(Story story) {
        storyRepository.save(story);
    }
}