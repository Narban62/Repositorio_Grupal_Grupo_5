package game_package;

import java.awt.Graphics;
import interface_package.*;

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