package ec.edu.uce.JuegoMain.game_package;




import ec.edu.uce.JuegoMain.interface_package.*;
import ec.edu.uce.JuegoMain.model_package.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Container {
	private Hero hero;
	private Bullet bullet;
	private List<Opponents> opponents;
	private Random random;
	ServerConnection serverConnectioncon = new ServerConnection();

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

	public void passInfo(HeroData heroData) {
		serverConnectioncon.sendHeroData(heroData);
	}

}
