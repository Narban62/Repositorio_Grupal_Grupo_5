package ec.edu.uce.Proyecto_Juego.controller_package;



import ec.edu.uce.Proyecto_Juego.model_package.objects.Bullet;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Hero;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Opponents;

import javax.swing.*;
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

    //Opponents opponent = new Opponents();

    public  Container() {


    }
    public Hero getHero() {
        return this.hero;
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

    public int getLevel() {
        return level;
    }

    public void creatOpponents(int level) {

        if (level == 1 && opponents.isEmpty()) {

            for (int i = 0; i< 5; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0));
            }
            this.level++;


        } else if (level == 2 && opponents.isEmpty()) {

            for (int i = 0; i< 10; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0));
                checkCollisionsLevel2();
            }
            this.level++;
            JOptionPane.showMessageDialog(null, "Nivel 2");


        } else if (level == 3 && opponents.isEmpty()) {

            for (int i = 0; i< 1; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), 0));
            }
            this.level++;
            JOptionPane.showMessageDialog(null, "Nivel 3");

        }else if (opponents.isEmpty()) {

            JOptionPane.showMessageDialog(null, "You Win");
            System.exit(0);

        }


    }

    public List<Opponents> getOpponents() {
        return opponents;
    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);

    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int variable) {
        for (int i = 0; i< opponents.size(); i++) {
            opponents.get(i).moveDown( variable);
        }

        for (int i = 0; i < bulletsOpponets.size(); i++) {
            bulletsOpponets.get(i).moveDown( variable*2);
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
    public void checkCollisionsLevel2() {
        // Iterar sobre todas las balas del héroe
        for (int i = 0; i < bulletsHero.size(); i++) {
            Bullet heroBullet = bulletsHero.get(i);
            Rectangle heroBulletBounds = heroBullet.getBounds();

            for (int j = 0; j < opponents.size(); j++) {
                Opponents opponent = opponents.get(j);
                Rectangle opponentBounds = opponent.getBounds();

                if (opponentBounds.intersects(heroBulletBounds)) {
                    // Incrementar el contador de colisiones del oponente
                    opponent.incrementCollisionCount();

                    // Si el oponente ha colisionado 3 veces, eliminarlo
                    if (opponent.getCollisionCount() >= 3) {
                        opponents.remove(opponent);
                        j--; // Decrementar j para evitar ConcurrentModificationException
                    }

                    // Eliminar la bala
                    bulletsHero.remove(heroBullet);
                    i--; // Decrementar i para evitar ConcurrentModificationException

                    // Salir del bucle interno ya que ya hemos manejado la colisión
                    break;
                }
            }
        }

        // Comprobar colisiones entre las balas de los oponentes y el héroe
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
