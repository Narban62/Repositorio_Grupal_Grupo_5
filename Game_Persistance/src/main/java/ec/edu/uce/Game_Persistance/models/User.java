package ec.edu.uce.Game_Persistance.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String user;
    private int level;
    private int score;
    private int life;



    public User(){

    }
    
    public User(int id, String user, int level, int score, int life){
        this.id = id;
        this.user = user;
        this.level = level;
        this.score = score;
        this.life = life;
    }
    public User(String user, int level,  int score, int life){
        this.user = user;
        this.level = level;
        this.score = score;
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", level=" + level +
                ", score=" + score +
                ", life=" + life +
                '}';
    }
}
