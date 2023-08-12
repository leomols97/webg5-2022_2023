package be.he2b.scrum.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

    @Id
    @SequenceGenerator(name = "SPRINT_SEQ", sequenceName = "sprint_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPRINT_SEQ")
    private int id;

    @Min(value = 1, message = "Number must be greater than 0")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Min(value = 1, message = "Days must be greater than 0")
    private int days;

    @OneToMany(mappedBy = "sprint")
    private Collection<Story> stories;

    @ManyToOne(optional = false)
    @JsonManagedReference
    private Project project;

    @Override
    public String toString() {
        return "Sprint [id=" + id + ", number=" + number + ", days=" + days + "]";
    }

}
