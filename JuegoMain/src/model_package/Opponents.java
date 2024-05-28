package game_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import Interfaces_game.IDrawable;
import Interfaces_game.IDead;
import Interfaces_game.IMovable;
import Interfaces_game.IShootable;

public class Villain implements IDrawable, IMovable, IDead, IShootable {

	private List<Point> positions;
	private List<Integer> directions;
	private static List<Bullet> bullets = new ArrayList<>();
	private int minX, maxX, score;

	public Villain(int numEnemies, int maxX, int maxY) {
		this.maxX = maxX;
		positions = new ArrayList<>();
		directions = new ArrayList<>();

		//Creacion de enemigos en posiciones aleatorias.
		Random random = new Random();
		for (int i = 0; i < numEnemies; i++) {
			int x = random.nextInt(maxX - 50);
			int y = random.nextInt((int) (350 * 0.66));
			positions.add(new Point(x, y));

			int direction = random.nextBoolean() ? -1 : 1;
			directions.add(direction);
		}
		

		Timer timer = new Timer(3000, e -> {
			shoot();
		});
		timer.start();
	}

	@Override
	public void draw(Graphics g) {
		for (Point position : positions) {
			// Dibujar un triángulo
			g.setColor(Color.GREEN);
			g.fillRect((int) position.getX(), (int) position.getY(), 25, 25);
		}
	}

	@Override
	public void die() {
	    // Itera sobre las posiciones de los enemigos
	    Iterator<Point> enemyIterator = positions.iterator();
	    while (enemyIterator.hasNext()) {
	        Point enemyPosition = enemyIterator.next();
	        // Crea un rectángulo que representa los límites del enemigo
	        Rectangle enemyBounds = new Rectangle((int) enemyPosition.getX(), (int) enemyPosition.getY(), 25, 25);
	        
	        // Itera sobre las balas del jugador
	        Iterator<Bullet> bulletIterator = Hero.getBullets().iterator();
	        while (bulletIterator.hasNext()) {
	            Bullet bullet = bulletIterator.next();
	            // Crea un rectángulo que representa los límites de la bala
	            Rectangle bulletBounds = new Rectangle(bullet.getX(), bullet.getY(), 7, 13);
	            
	            // Verifica si la bala intersecta con el enemigo
	            if (bulletBounds.intersects(enemyBounds)) {
	                // Elimina el enemigo y la bala de sus respectivas listas
	                enemyIterator.remove();
	                bulletIterator.remove();
	                // Incrementa el puntaje
	                score += 10;
	            }
	        }
	    }
	}


	@Override
	public void movements(String directionG) {
		// Mover los enemigos independientemente
		for (int i = 0; i < positions.size(); i++) {
			Point position = positions.get(i);
			int currentDirection = directions.get(i);

			// Cambiar de dirección al alcanzar los bordes
			if (position.getX() <= minX || position.getX() >= maxX - 40) {
				currentDirection *= -1;
				directions.set(i, currentDirection);
			}

			// Mover en la dirección actual
			position.setLocation(position.getX() + currentDirection, position.getY());
		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		for (Point position : positions) {
			bullets.add(new Bullet((int) position.getX() + 10, (int) position.getY()));
		}

	}

	public void updateBullets() {
		//for para que las balas se muevan hacia abajo
		for (Bullet bullets : bullets) {
			bullets.movements("DOWN");
		}
	}

	public List<Bullet> getBullets() {
		return bullets;
	}
	

	public int getScore() {
		return score;
	}

	public boolean noMoreEnemies() {
		return positions.isEmpty();
	}
	
	public boolean checkCollision(Bullet bullet) {
	    Iterator<Point> enemyIterator = positions.iterator();
	    while (enemyIterator.hasNext()) {
	        Point enemyPosition = enemyIterator.next();
	        Rectangle enemyBounds = new Rectangle((int) enemyPosition.getX(), (int) enemyPosition.getY(), 25, 25);
	        
	        Rectangle bulletBounds = new Rectangle(bullet.getX(), bullet.getY(), 7, 13);
	        
	        if (bulletBounds.intersects(enemyBounds)) {
	            enemyIterator.remove(); // Elimina al enemigo
	            score += 10; // Incrementa el puntaje
	            return true;
	        }
	    }
	    return false;
	}
}