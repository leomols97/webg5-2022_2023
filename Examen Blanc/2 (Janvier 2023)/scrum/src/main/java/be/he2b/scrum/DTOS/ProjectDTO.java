package be.he2b.scrum.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
/**
 * Pour avoir les statistiques d'un projet (voir la fonciton
 * 'findProjectWithNameBegin' du fichier 'StoryRepository.java')
 * Examen blanc Q1.b
 */
public class ProjectDTO {
    private String projectName;
    private long sprintQantity;
    private long storyQantity;
}