package be.he2b.scrum.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import be.he2b.scrum.model.Project;
import be.he2b.scrum.model.Sprint;

public interface SprintRepository extends CrudRepository<Sprint, Integer> {

    Collection<Sprint> findAll();

    Collection<Sprint> findByProject(Project project);
}
