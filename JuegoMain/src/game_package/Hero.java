package game_package;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import Interfaces_game.IDrawable;
import Interfaces_game.IDead;
import Interfaces_game.IMovable;
import Interfaces_game.IShootable;

public class Hero implements IDrawable, IShootable, IMovable, IDead {

	private int x, y, life;
	private String names;
	private boolean isAlive;
	private static List<Bullet> bullets = new ArrayList<>();
	private Timer shootTimer;


	public Hero(int x, int y, int health, String name) {
		this.x = x;
		this.y = y;
		this.isAlive = true;
		this.life = health;
		this.names = name;
		shootTimer = new Timer(100, e -> {
			shootTimer.stop(); // Detener el temporizador
		});
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		// Dibujar un triángulo
		int[] xPoints = { x, x + 10, x - 10 };
		int[] yPoints = { y, y + 20, y + 20 };
		g.setColor(Color.CYAN);
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