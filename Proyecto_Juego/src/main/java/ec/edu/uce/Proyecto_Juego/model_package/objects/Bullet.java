package ec.edu.uce.Proyecto_Juego.model_package.objects;


import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IDrawable;
import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IMovable;

import java.awt.*;


public class Bullet implements IDrawable, IMovable {

    private int x;
    private int y;

    public Bullet(FaherObjects faherObjects, int addBulletAtPosition) {
        if (faherObjects instanceof Hero) {
            x = faherObjects.getPointsX()[0];
            y = faherObjects.getPointsY()[0];

        } else if (faherObjects instanceof Opponents) {

            x = faherObjects.getPointsX()[4] + addBulletAtPosition;
            y = faherObjects.getPointsY()[4];

        }

    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 15, 15);
    }

    @Override
    public void draw(Graphics graphics, IDrawable drawable) {

    }

    @Override
    public void moveUp(int variable) {

        y -= variable;
    }

    @Override
    public void moveDown(int variable) {

        y += variable;
    }

    @Override
    public void moveLeft(int variable) {

    }

    @Override
    public void moveRight(int variable) {

    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, 15, 15);
    }
}