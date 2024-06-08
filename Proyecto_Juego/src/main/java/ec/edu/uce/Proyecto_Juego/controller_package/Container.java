package ec.edu.uce.Proyecto_Juego.controller_package;



import ec.edu.uce.Proyecto_Juego.model_package.objects.Bullet;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Hero;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Opponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Container {
    final int SCREEN_WITDH =700;
    final int SCREEN_HEIGHT =200;

    Hero hero = new Hero();
    List<Opponents> opponents = new ArrayList<Opponents>();
    List<Bullet> bulletsHero = new ArrayList<Bullet>();
    List<Bullet> bulletsOpponets = new ArrayList<Bullet>();
    Random random = new Random();

    // varaible de levels
    private int level = 1;

    //Opponents opponet = new Opponents();

    public  Container() {


    }


    public void draw(Graphics graphics) {
        hero.draw(graphics);

        creatOpponents(level);

        for (int i = 0; i< opponents.size(); i++) {
            opponents.get(i).draw(graphics);
        }

        for (int i = 0; i< bulletsHero.size(); i++) {
            bulletsHero.get(i).draw(graphics);
        }

        for (int i = 0; i< bulletsOpponets.size(); i++) {
            bulletsOpponets.get(i).draw(graphics);
        }

    }

    public void creatOpponents(int level) {

        if (level == 1 && opponents.isEmpty()) {

            for (int i = 0; i< 5; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), random.nextInt(SCREEN_HEIGHT)));
            }
            this.level++;

        } else if (level == 2 && opponents.isEmpty()) {

            for (int i = 0; i< 10; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), random.nextInt(SCREEN_HEIGHT)));
            }
            this.level++;

        } else if (level == 3 && opponents.isEmpty()) {

            for (int i = 0; i< 1; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), random.nextInt(SCREEN_HEIGHT)));
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
        for (int i = 0; i< opponents.size(); i++) {
            opponents.get(i).moveDown(variable);
        }

        for (int i = 0; i < bulletsOpponets.size(); i++) {
            bulletsOpponets.get(i).moveDown(variable*30);
        }

    }

    public void moveUp(int variable) {

        for (int i = 0; i < bulletsHero.size(); i++) {
            bulletsHero.get(i).moveUp(variable);
        }

    }

    public void createShootHero() {
        bulletsHero.add(new Bullet(hero));
    }

    public void createShootOpponents() {

        if(!opponents.isEmpty()){
            //bulletsOpponets.add(new Bullet(opponents.get(randomOpponnent)));

            for (int i = 0; i <4; i++) {
                int randomOpponnent = random.nextInt(opponents.size());
                bulletsOpponets.add(new Bullet(opponents.get(randomOpponnent)));
            }

            if (level == 3){
                bulletsOpponets.add(new Bullet(opponents.get(0)));
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
                    // Eliminar la bala y el oponente

                    bulletsHero.remove(heroBullet);
                    opponents.remove(opponent);

                    // Decrementar j para evitar ConcurrentModificationException
                    j--;
                    // Salir del bucle interno ya que ya hemos manejado la colisión
                    break;
                }
            }
        }



        for (int i = 0; i < bulletsOpponets.size(); i++) {

            Bullet opponentBullet = bulletsOpponets.get(i);
            Rectangle opponentBulletBounds = opponentBullet.getBounds();

            Rectangle heroBounds = hero.getBounds();

            if (heroBounds.intersects(opponentBulletBounds)){
                hero.reduceLife(10);
                bulletsOpponets.remove(opponentBullet);

            }

        }

    }



}
