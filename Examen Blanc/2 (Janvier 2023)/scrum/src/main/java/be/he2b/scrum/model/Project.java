package be.he2b.scrum.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Pour avoir un constructeur dans la classe Story. Il nous faut donc un
      // constructeur par défaut sans paramètre pcq java n'en génère plus un par
      // défaut
@NoArgsConstructor
public class Project {

    @Id
    private String name;

    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private Collection<Sprint> sprints;
}
