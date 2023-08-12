package be.he2b.scrum.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    private String name;
    private boolean active;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<Sprint> sprints;
}
