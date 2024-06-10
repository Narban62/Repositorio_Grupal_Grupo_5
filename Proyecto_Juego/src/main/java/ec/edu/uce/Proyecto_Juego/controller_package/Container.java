package ec.edu.uce.Proyecto_Juego.controller_package;


import ec.edu.uce.Proyecto_Juego.model_package.objects.Bullet;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Hero;
import ec.edu.uce.Proyecto_Juego.model_package.objects.LevelManager;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Opponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Container {
    final int SCREEN_WITDH = 700;
    final int SCREEN_HEIGHT = 200;

    Hero hero = new Hero();
    List<Opponents> opponents = new ArrayList<Opponents>();
    List<Bullet> bulletsHero = new ArrayList<Bullet>();
    List<Bullet> bulletsOpponets = new ArrayList<Bullet>();
    List<LevelManager> levelManagers = new ArrayList<LevelManager>();
    Random random = new Random();

    // varaible de levels
    private int level;
    private int inncrement;

    //Opponents opponet = new Opponents();

    public Container() {

        /*0*/
        levelManagers.add(new LevelManager(1, 5, 100, 1, 5, 5));
        /*1*/
        levelManagers.add(new LevelManager(2, 10, 34, 2, 10, 10));
        /*2*/
        levelManagers.add(new LevelManager(3, 1, 100, 3, 15, 15));
        level = levelManagers.get(0).getLevel();
    }

    public void resetGame() {
        // Restablecer todas las variables del juego a su estado inicial
        // Esto dependerá de cómo hayas implementado tu juego
        // Aquí hay algunos ejemplos:
        this.hero.resetall(); // Suponiendo que tienes un método reset en la clase Hero
        this.opponents.clear(); // Suponiendo que opponents es una lista
        this.level = 1; // Suponiendo que tienes una variable de nivel
        // Añade aquí cualquier otra variable que necesites restablecer
    }

    public void draw(Graphics graphics) {

        hero.draw(graphics);

        creatOpponents(level);

        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).draw(graphics);
        }
        for (int i = 0; i < bulletsHero.size(); i++) {
            bulletsHero.get(i).draw(graphics);
        }
        for (int i = 0; i < bulletsOpponets.size(); i++) {
            bulletsOpponets.get(i).draw(graphics);
        }

    }

    public void creatOpponents(int level) {

        if (level == 1 && opponents.isEmpty()) {

            hero.setLevelName("Nivel 1");
            for (int i = 0; i < levelManagers.get(level - 1).getNumberOfOpponents(); i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0, level));
            }
            this.level++;

        } else if (level == 2 && opponents.isEmpty()) {

            hero.setLevelName("Nivel 2");
            for (int i = 0; i < levelManagers.get(level - 1).getNumberOfOpponents(); i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0, level));
            }

            this.level++;

        } else if (level == 3 && opponents.isEmpty()) {

            hero.setLevelName("Nivel 3");
            for (int i = 0; i < levelManagers.get(level - 1).getNumberOfOpponents(); i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0, level));
            }
            this.level++;

        }

    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);

    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int variable) {
        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).moveDown(variable);
        }

        for (int i = 0; i < bulletsOpponets.size(); i++) {
            bulletsOpponets.get(i).moveDown(variable * 30);
        }

    }

    public void moveUp(int variable) {

        for (int i = 0; i < bulletsHero.size(); i++) {
            bulletsHero.get(i).moveUp(variable);
        }

    }

    public void createShootHero() {

        if (hero.getLife() > 0) {
            bulletsHero.add(new Bullet(hero, 0));
        }


    }

    public void createShootOpponents() {

        if (!opponents.isEmpty()) {

            if (level - 1 == 1) {

                int randomOpponnent = random.nextInt(opponents.size());
                inncrement = 0;

                for (int i = 0; i < levelManagers.get(level - 2).getNumberShoot(); i++) {
                    bulletsOpponets.add(new Bullet(opponents.get(randomOpponnent), inncrement));
                    inncrement += opponents.get(randomOpponnent).getWidghtOpponent() / 2;
                }

            } else if (level - 1 == 2) {

                int randomOpponnent = random.nextInt(opponents.size());
                inncrement = 0;

                for (int i = 0; i < levelManagers.get(level - 2).getNumberShoot(); i++) {
                    bulletsOpponets.add(new Bullet(opponents.get(randomOpponnent), inncrement));
                    inncrement += opponents.get(randomOpponnent).getWidghtOpponent() / 2;
                }

            } else if (level - 1 == 3) {

                inncrement = 0;
                for (int i = 0; i < levelManagers.get(level - 2).getNumberShoot(); i++) {
                    bulletsOpponets.add(new Bullet(opponents.get(0), inncrement));
                    inncrement += opponents.get(0).getWidghtOpponent() / 2;
                }

            }

        }

    }


    public void checkCollisions() {
        // Iterar sobre todas las balas del héroe
        for (int i = 0; i < bulletsHero.size(); i++) {
            Bullet heroBullet = bulletsHero.get(i);
            Rectangle heroBulletBounds = heroBullet.getBounds();

            // Iterar sobre todos los oponentes
            for (int j = 0; j < opponents.size(); j++) {
                Opponents opponent = opponents.get(j);
                Rectangle opponentBounds = opponent.getBounds();

                // Si hay una colisión entre una bala del héroe y un oponente
                if (heroBulletBounds.intersects(opponentBounds)) {

                    if (level - 1 == 1) {

                        opponent.reduceLifeOpponnet(levelManagers.get(level - 2).getShotsToDestroy());
                        bulletsHero.remove(heroBullet);
                        hero.addScore(levelManagers.get(level - 2).getScorePerOpponnetDestroyed());

                        if (opponent.getLifeOpponent() <= 0) {
                            opponents.remove(opponent);
                        }


                    } else if (level - 1 == 2) {

                        opponent.reduceLifeOpponnet(levelManagers.get(level - 2).getShotsToDestroy());
                        bulletsHero.remove(heroBullet);
                        hero.addScore(levelManagers.get(level - 2).getScorePerOpponnetDestroyed());

                        if (opponent.getLifeOpponent() <= 0) {
                            opponents.remove(opponent);
                        }

                    } else if (level - 1 == 3) {

                        hero.addScore(levelManagers.get(level - 2).getScorePerOpponnetDestroyed());

                        if (hero.getLife() < 50) {

                            opponent.reduceLifeOpponnet(5);
                            bulletsHero.remove(heroBullet);

                            if (opponent.getLifeOpponent() <= 0) {
                                opponents.remove(opponent);
                            }


                        } else if (hero.getLife() >= 50 && hero.getLife() <= 75) {

                            opponent.reduceLifeOpponnet(10);
                            bulletsHero.remove(heroBullet);

                            if (opponent.getLifeOpponent() <= 0) {
                                opponents.remove(opponent);
                            }

                        } else if (hero.getLife() > 75) {

                            opponent.reduceLifeOpponnet(15);
                            bulletsHero.remove(heroBullet);

                            if (opponent.getLifeOpponent() <= 0) {
                                opponents.remove(opponent);
                            }

                        }


                    }
                    // Salir del bucle interno ya que ya hemos manejado la colisión
                    break;
                }

            }
        }


        for (int i = 0; i < bulletsOpponets.size(); i++) {

            Bullet opponentBullet = bulletsOpponets.get(i);
            Rectangle opponentBulletBounds = opponentBullet.getBounds();

            Rectangle heroBounds = hero.getBounds();

            if (heroBounds.intersects(opponentBulletBounds)) {

                if (hero.getLife() > 0) {

                    if (level - 1 == 1) {
                        hero.reduceLife(levelManagers.get(level - 2).getDamagePerShot());
                        bulletsOpponets.remove(opponentBullet);
                    } else if (level - 1 == 2) {
                        hero.reduceLife(levelManagers.get(level - 2).getDamagePerShot());
                        bulletsOpponets.remove(opponentBullet);
                    } else if (level - 1 == 3) {
                        hero.reduceLife(levelManagers.get(level - 2).getDamagePerShot());
                        bulletsOpponets.remove(opponentBullet);
                    }

                }

            }

        }

    }

    public boolean ckeckColissionLine() {

        for (int i = 0; i < opponents.size(); i++) {

            if (opponents.get(i).getMax_Y_Opponent() >= hero.getLineHeigth()) {
                opponents.remove(opponents.get(i));
                hero.reduceLife(100);
                return true;
            }

        }

        return false;

    }

    public int getScoreFromHero() {
        return hero.getScore();
    }

    public int getLifeFromHero() {
        return hero.getLife();
    }

    public boolean areAllLevel3OpponentsEliminated() {
        // Si estamos en el nivel 3 y no hay oponentes, entonces todos los oponentes del nivel 3 han sido eliminados
        return level - 1 == 3 && opponents.isEmpty();
    }
}
