package be.he2b.scrum.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data // Pour avoir un constructeur dans la classe Story. Il nous faut donc un
      // constructeur par défaut sans paramètre pcq java n'en génère plus un par
      // défaut
public class Sprint {

    @Id
    @GeneratedValue(generator = "sprint_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    @Positive(message = "Sprint number must be positive")
    private int number;

    @Positive(message = "Days for a sprint must be positive")
    private int days;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint")
    private Collection<Story> stories;

    @ManyToOne(optional = false) // Pour dire que la liste peut être null
    private Project project;

}
