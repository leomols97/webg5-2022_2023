package be.he2b.scrum.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import be.he2b.scrum.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    public Collection<Project> findAll();
}