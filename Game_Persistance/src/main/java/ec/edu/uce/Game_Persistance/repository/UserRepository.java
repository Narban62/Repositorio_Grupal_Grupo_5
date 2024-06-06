package ec.edu.uce.Game_Persistance.repository;

import ec.edu.uce.Game_Persistance.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.user = ?1")
    User findByUser(String lastname);
}
