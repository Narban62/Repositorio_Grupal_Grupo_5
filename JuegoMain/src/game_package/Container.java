package game_package;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import interface_package.*;
import model_package.Bullet;
import model_package.Hero;
import model_package.Opponents;

public class Container {
	private Hero hero;
	private Bullet bullet;
	private List<Opponents> opponents;
	private Random random;

	public Container(Hero hero, Bullet bullet, List<Opponents> opponents, Random random) {
		this.hero = hero;
		this.bullet = bullet;
		this.opponents = opponents;
		this.random = random;
	}

	public void draw(IDrawable drawable, Graphics g) {
		drawable.draw(g);
	}

	public void move(IMovable movable, String directionG) {
		movable.movements(directionG);
	}

	public void shoot(IShootable shootable) {
		shootable.shoot();
	}

	public void dead(IDead dead) {
		dead.die();
	}
}
