package ec.edu.uce.Game_Persistance.repository;

import ec.edu.uce.Game_Persistance.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.user = ?1")
    User findByUser(String lastname);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.level = ?1, u.score = ?2 WHERE u.user = ?3")
    void updateScore(int level, int score, String user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.user = ?1")
    void deleteUserByName(String username);

}
