package be.he2b.scrum.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import be.he2b.scrum.DTO.ProjectInfos;
import be.he2b.scrum.model.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {

    Collection<Project> findAll();

    @Query("""
                SELECT new be.he2b.scrum.DTO.ProjectInfos(p.name, p.active, COUNT(DISTINCT s), COUNT(st))
                FROM Project p
                JOIN p.sprints s
                JOIN s.stories st
                WHERE p.name LIKE :prefix%
                GROUP BY p.name
            """)
    Collection<ProjectInfos> numberSprintsAndStoriesInAllProjects(@Param("prefix") String namePrefix);

    Project findByName(String projectName);

    long countByName(String projectName);
}