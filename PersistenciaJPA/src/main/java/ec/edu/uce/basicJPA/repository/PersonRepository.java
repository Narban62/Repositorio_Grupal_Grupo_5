package ec.edu.uce.basicJPA.repository;

import ec.edu.uce.basicJPA.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long> {
}
