package be.he2b.scrum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {

    @Id
    @SequenceGenerator(name = "STORY_SEQ", sequenceName = "story_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORY_SEQ")
    private int id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @Min(value = 1, message = "Estimate must be greater than 0")
    private int estimate;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Sprint sprint;

    @Override
    public String toString() {
        return "Story [id=" + id + ", title=" + title + ", estimate=" + estimate + "]";
    }

}
