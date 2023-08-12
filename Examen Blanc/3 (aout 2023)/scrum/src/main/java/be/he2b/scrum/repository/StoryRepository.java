package be.he2b.scrum.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import be.he2b.scrum.model.Sprint;
import be.he2b.scrum.model.Story;

public interface StoryRepository extends CrudRepository<Story, Integer> {

    Collection<Story> findBySprint(Sprint sprint);
}