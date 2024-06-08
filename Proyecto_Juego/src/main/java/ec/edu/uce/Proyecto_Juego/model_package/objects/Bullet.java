package ec.edu.uce.Proyecto_Juego.model_package.objects;


import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IDrawable;
import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IMovable;

import java.awt.*;


public class Bullet implements IDrawable, IMovable {

    public int x;
    public int y;

    public Bullet(FatherObjects fatherObjects) {

        if (fatherObjects instanceof Hero) {
            x = fatherObjects.getPointsX()[0];
            y = fatherObjects.getPointsY()[0];

        } else if (fatherObjects instanceof Opponents) {
            x = fatherObjects.getPointsX()[3];
            y = fatherObjects.getPointsY()[3];
        }

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 15,15);
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


