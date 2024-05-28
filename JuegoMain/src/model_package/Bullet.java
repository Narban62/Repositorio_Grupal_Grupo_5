package game_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Interfaces_game.IDrawable;
import Interfaces_game.IMovable;

public class Bullet implements IDrawable, IMovable {
	
	private int x, y;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 7, 13);
		g.drawOval(x, y, 7, 13);

	}

	@Override
	public void movements(String directionG) {
		// TODO Auto-generated method stub
		switch (directionG) {
		case "UP":
			y -= 10;
			if (y < -15) {
				y = -15;
			}
			break;

		case "DOWN":
			y += 10;
			if (y > 570) {
				y = 570;
			}
			break;
		}

	}

	public boolean collision(Hero hero) {
		Rectangle bulletBounds = new Rectangle(x, y, 7, 13);
		Rectangle playerBounds = new Rectangle(hero.getX(), hero.getY(), 30, 30);
		return bulletBounds.intersects(playerBounds);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
