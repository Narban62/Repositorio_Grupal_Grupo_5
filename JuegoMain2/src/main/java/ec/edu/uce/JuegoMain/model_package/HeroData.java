package ec.edu.uce.JuegoMain.model_package;

public class HeroData {

    private String name;
    private int health;
    private int score;

    public HeroData(String name, int health, int score) {
        this.name = name;
        this.health = health;
        this.score = score;
    }

    public HeroData (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
