package be.he2b.scrum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Pour avoir un constructeur dans la classe Story. Il nous faut donc un
      // constructeur par défaut sans paramètre pcq java n'en génère plus un par
      // défaut
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(generator = "story_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    @NotBlank(message = "Title must not be blank") // @NotEmpty aurait été bon également
    private String title;

    @Positive(message = "Story time needed must be positive")
    private int estimate;

    @ManyToOne(optional = false) // Pour dire que la story est obligatoirement liée à un projet. Le sprint ne
                                 // peut pas être null
    private Sprint sprint;
}