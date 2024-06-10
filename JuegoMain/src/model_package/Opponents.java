package model_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import interface_package.*;

public class Opponents implements IDrawable, IMovable, IDead, IShootable {
	private List<Point> positions;
	private List<Integer> directions;
	private static List<Bullet> bullets = new ArrayList<>();
	private int minX, maxX, score;

	public Opponents() {
	}

	public Opponents(int numEnemies, int maxX, int maxY) {
		this.maxX = maxX;
		this.minX = 0;
		positions = new ArrayList<>();
		directions = new ArrayList<>();
		Random random = new Random();

		// Creación de enemigos en posiciones aleatorias
		for (int i = 0; i < numEnemies; i++) {
			int x = random.nextInt(maxX - 50);
			int y = random.nextInt((int) (350 * 0.66));
			positions.add(new Point(x, y));

			int direction = random.nextBoolean() ? -1 : 1;
			directions.add(direction);
		}

		Timer timer = new Timer(3000, e -> shoot());
		timer.start();
	}

	@Override
	public void draw(Graphics g) {
		for (Point position : positions) {
			g.setColor(Color.GREEN);
			int[] xPoints = {(int) position.getX(), (int) position.getX() + 10, (int) position.getX() + 20, (int) position.getX() + 30, (int) position.getX() + 40};
			int[] yPoints = {(int) position.getY(), (int) position.getY() + 25, (int) position.getY(), (int) position.getY() + 25, (int) position.getY()};
			g.fillPolygon(xPoints, yPoints, 5);
		}
	}

	@Override
	public void die() {
		// Verificar y manejar la colisión de balas del héroe con los enemigos
		Iterator<Point> enemyIterator = positions.iterator();
		while (enemyIterator.hasNext()) {
			Point enemyPosition = enemyIterator.next();
			Rectangle enemyBounds = new Rectangle((int) enemyPosition.getX(), (int) enemyPosition.getY(), 25, 25);
			Iterator<Bullet> bulletIterator = Hero.getBullets().iterator();
			while (bulletIterator.hasNext()) {
				Bullet bullet = bulletIterator.next();
				Rectangle bulletBounds = new Rectangle(bullet.getX(), bullet.getY(), 7, 13);
				if (bulletBounds.intersects(enemyBounds)) {
					enemyIterator.remove();
					bulletIterator.remove();
					score += 10;
				}
			}
		}
	}

	@Override
	public void movements(String directionG) {
		for (int i = 0; i < positions.size(); i++) {
			Point position = positions.get(i);
			int currentDirection = directions.get(i);
			if (position.getX() <= minX || position.getX() >= maxX - 40) {
				currentDirection *= -1;
				directions.set(i, currentDirection);
				position.setLocation(position.getX(), position.getY() + 10);
			}
			position.setLocation(position.getX() + currentDirection, position.getY());
		}
	}

	@Override
	public void shoot() {
		for (Point position : positions) {
			bullets.add(new Bullet((int) position.getX() + 10, (int) position.getY()));
		}
	}

	public void updateBullet() {
		for (Bullet bullet : bullets) {
			bullet.update();
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
				enemyIterator.remove();
				score += 5;
				return true;
			}
		}
		return false;
	}
}
