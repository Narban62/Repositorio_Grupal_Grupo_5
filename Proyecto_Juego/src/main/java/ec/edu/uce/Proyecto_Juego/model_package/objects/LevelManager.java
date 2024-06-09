package ec.edu.uce.Proyecto_Juego.model_package.objects;

public class LevelManager {

    private int level;  // nivel
    private int numberOfOpponents; // numero de oponentes
    private int shotsToDestroy; // disparos para morir
    private int numberShoot; // numero de disparos
    private int damagePerShot; // daño por disparo al hero
    private int scorePerOpponnetDestroyed; // puntuacion por oponente destruido

    public LevelManager(int level, int numberOfOpponents, int shotsToDestroy, int numberShoot, int damagePerShot, int scorePerOpponnetDestroyed) {
        this.level = level;
        this.numberOfOpponents = numberOfOpponents;
        this.shotsToDestroy = shotsToDestroy;
        this.numberShoot = numberShoot;
        this.damagePerShot = damagePerShot;
        this.scorePerOpponnetDestroyed = scorePerOpponnetDestroyed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfOpponents() {
        return numberOfOpponents;
    }

    public void setNumberOfOpponents(int numberOfOpponents) {
        this.numberOfOpponents = numberOfOpponents;
    }

    public int getShotsToDestroy() {
        return shotsToDestroy;
    }

    public void setShotsToDestroy(int shotsToDestroy) {
        this.shotsToDestroy = shotsToDestroy;
    }

    public int getNumberShoot() {
        return numberShoot;
    }

    public void setNumberShoot(int numberShoot) {
        this.numberShoot = numberShoot;
    }

    public int getDamagePerShot() {
        return damagePerShot;
    }

    public void setDamagePerShot(int damagePerShot) {
        this.damagePerShot = damagePerShot;
    }

    public int getScorePerOpponnetDestroyed() {
        return scorePerOpponnetDestroyed;
    }

    public void setScorePerOpponnetDestroyed(int scorePerOpponnetDestroyed) {
        this.scorePerOpponnetDestroyed = scorePerOpponnetDestroyed;
    }
}
