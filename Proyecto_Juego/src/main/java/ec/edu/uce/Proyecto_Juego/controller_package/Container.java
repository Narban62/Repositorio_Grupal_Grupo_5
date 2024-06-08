package ec.edu.uce.Proyecto_Juego.controller_package;

import ec.edu.uce.Proyecto_Juego.model_package.objects.Bullet;
import ec.edu.uce.Proyecto_Juego.model_package.objects.Hero;
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
    List<Bullet> bulletsOpponents = new ArrayList<Bullet>();
    Random random = new Random();
    //VAROABLE LEVEL
    private int level = 1;

    //Opponents opponet = new Opponents();

    public Container() {
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);
        createOpponents(level);
        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).draw(graphics);
        }
        for (int i = 0; i < bulletsHero.size(); i++) {
            bulletsHero.get(i).draw(graphics);
        }
        for (int i = 0; i < bulletsOpponents.size(); i++) {
            bulletsOpponents.get(i).draw(graphics);
        }

    }

    public void createOpponents(int level) {
        if (level == 1 && opponents.isEmpty()){
            for (int i = 0; i < 5; i++) {
                opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), random.nextInt(SCREEN_HEIGHT)));
            }
        this.level++;
    }      else if(level ==2&&opponents.isEmpty())

    {
        for (int i = 0; i < 10; i++) {
            opponents.add(new Opponents(random.nextInt(SCREEN_WITDH), random.nextInt(SCREEN_HEIGHT)));

        }
        level++;

    }      else if(level ==3&&opponents.isEmpty())

    {
        for (int i = 0; i < 1; i++) {
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
        for(int i = 0; i < bulletsOpponents.size(); i++)
            bulletsOpponents.get(i).moveUp(variable*30);

    }

    public void moveUp(int variable) {

        for(int i = 0; i < bulletsHero.size(); i++)
            bulletsHero.get(i).moveUp(variable);

    }

    public void createShootHero() {
        bulletsHero.add(new Bullet(hero));

    }
    public void crateShootOpponents() {
        if(!opponents.isEmpty()) {
            bulletsOpponents.add(new Bullet(opponents.get(0)));
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

                    System.out.println("hola");
                    bulletsHero.remove(heroBullet);
                    opponents.remove(opponent);

                    // Decrementar j para evitar ConcurrentModificationException
                    j--;
                    // Salir del bucle interno ya que ya hemos manejado la colisión
                    break;
                }
            }
        }

    }




}
