package ec.edu.uce.JuegoMain.model_package;

import ec.edu.uce.JuegoMain.interface_package.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero implements IDrawable, IShootable, IMovable, IDead {
	private List<Point> positions;
	private int x,y,life;
	private String names;
	private boolean isAlive;
	private static List<Bullet> bullets = new ArrayList<>();
	private Timer shootTimer;

	public Hero(int x, int y, int health, String name) {
		this.x = x;
		this.y = y;
		positions = new ArrayList<>();
		this.isAlive = true;
		this.life = health;
		this.names = name;
		shootTimer = new Timer(100, e -> {
			shootTimer.stop(); // Detener el temporizador
		});
		positions.add(new Point(x, y));
	}

	@Override
	public void draw(Graphics g) {
		// Dibujar un triángulo
		int[] xPoints = { x, x + 10, x - 10 };
		int[] yPoints = { y, y + 20, y + 20 };
		g.setColor(Color.WHITE);
		g.fillPolygon(xPoints, yPoints, 3);
	}
	

	@Override
    public void shoot() {
        if (!shootTimer.isRunning()) {
            bullets.add(new Bullet(x + 8, y)); // Disparar una bala desde la posición del jugador
            shootTimer.start(); // Iniciar el temporizador
        }
    }

	public void update() {
		for (Bullet bullets : bullets) {
			bullets.movements("UP");
		}
	}

	public static List<Bullet> getBullets() {

		return bullets;
	}

    public boolean isAlive() {

		return life > 0;
    }
    public String getName() {

		return names;
    }

	@Override
	public void movements(String directionG) {
		switch (directionG) {
		case "UP":
			y -= 20;
			if (y < 15) {
				y = 15;
			}
			break;

		case "DOWN":
			y += 20;
			if (y > 540) {
				y = 540;
			}
			break;

		case "LEFT":
			x -= 25;
			if (x < 11) {
				x = 11;
			}
			break;

		case "RIGHT":
			x += 25;
			if (x > 772) {
				x = 772;
			}
			break;
		}
	}

	@Override
	public void die() {
		life -= 25;
		if (life < 0) {
			life = 0;
			isAlive = false;
		}
	}

	public boolean checkCollision(Bullet bullet) {
		Rectangle heroBounds = new Rectangle(x, y, 25, 25); // Asume que el héroe es un rectángulo de 25x25
		Rectangle bulletBounds = new Rectangle(bullet.getX(), bullet.getY(), 7, 13); // Asume que la bala es un rectángulo de 7x13

		if (bulletBounds.intersects(heroBounds)) {
			life -= 5; // Reducir la vida del héroe
			System.out.println("colision detectada y resta 10");

			// Verificar si la salud del héroe ha llegado a 0
			if (life <= 0) {
				isAlive = false; // Marcar al héroe como muerto
				System.out.println("el heroe ha muerto");
			}
			return true;
		}
		return false;
	}

	public void reduceHealth() {
		this.life -= 5; // Reduce la salud en 10
		if (this.life < 0) {
			this.life = 0; // Asegura que la salud no sea negativa
		}
	}

	public int getHealth() {

		return life;
	}

	public int getY() {

		return y;
	}

	public int getX() {

		return x;
	}

}