package ec.edu.uce.JuegoMain.model_package;

public class HeroData {

    private String user;
    private int level;
    private int score;

    public HeroData(String user, int level, int score) {
        this.user = user;
        this.level = level;
        this.score = score;
    }

    public HeroData (){

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
}
