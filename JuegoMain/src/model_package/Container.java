package game_package;

import java.awt.Graphics;
import java.awt.Graphics2D;
import Interfaces_game.IDrawable;
import Interfaces_game.IDead;
import Interfaces_game.IMovable;
import Interfaces_game.IShootable;

public class Container {

	public void draw(IDrawable dra, Graphics g) {
		dra.draw(g);
	}

	public void move(IMovable mov, String directionG) {
		mov.movements(directionG);
	}

	public void shoot(IShootable sh) {
		sh.shoot();
	}

	public void dead(IDead di) {
		di.die();
	}

}