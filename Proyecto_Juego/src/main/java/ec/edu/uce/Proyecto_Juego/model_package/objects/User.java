package ec.edu.uce.Proyecto_Juego.model_package.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String user;
    private int level;
    private int score;
    private int life;

    public User() {
    }

    public User(String user, int level, int score, int life) {
        this.user = user;
        this.level = level;
        this.score = score;
        this.life = life;
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
                "user='" + user + '\'' +
                ", level=" + level +
                ", score=" + score +
                ", life=" + life +
                '}';
    }
}
