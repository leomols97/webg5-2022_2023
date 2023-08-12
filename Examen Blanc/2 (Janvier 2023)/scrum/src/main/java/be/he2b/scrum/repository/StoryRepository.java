package be.he2b.scrum.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import be.he2b.scrum.DTOS.ProjectDTO;
import be.he2b.scrum.model.Story;

public interface StoryRepository extends CrudRepository<Story, Integer> {

    public Collection<Story> findBySprintProjectName(String projectName); // car, avec collection, il est possible
                                                                          // d'utiliser les fonctions set(), get(),
                                                                          // at(),... ce à quoi nous n'aurions pas accès
                                                                          // si nous retournions Iterable<Story>

    @Query("SELECT new be.he2b.scrum.DTOS.ProjectDTO( " // car il faut retourner un objet de type ProjectDTO et non de
                                                        // type Object()
            + " story.sprint.project.name , " // Nom du projet
            + " COUNT ( DISTINCT story.sprint ) , " // Nombre de sprints
            + " COUNT ( DISTINCT story.id ) ) " // Nombre de stories

            + " FROM Story story "
            + " RIGHT JOIN Sprint sprint ON story.sprint.id = sprint.id " // RIGHT JOIN nécessaire pour faire en sorte
                                                                          // que TOUS les projets soient
                                                                          // retournés, même ceux qui n'ont pas de
                                                                          // stories et non pas uniquement le premier
            + " RIGHT JOIN Project project ON sprint.project.id = project.id " // RIGHT JOIN nécessaire pour faire en
                                                                               // sorte que TOUS les projets soient
                                                                               // retournés, même ceux qui n'ont pas de
                                                                               // stories et non pas uniquement le
                                                                               // premier

            + " WHERE story.sprint.project.name LIKE :projectNamePrefix% "

            + " GROUP BY story.sprint.project.name")
    Collection<ProjectDTO> findProjectWithNameBegin(@Param("projectNamePrefix") String projectNamePrefix);

}