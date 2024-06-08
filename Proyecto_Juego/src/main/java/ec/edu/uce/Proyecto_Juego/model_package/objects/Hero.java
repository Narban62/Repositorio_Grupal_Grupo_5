package ec.edu.uce.Proyecto_Juego.model_package.objects;

import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IDrawable;
import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IMovable;

import java.awt.*;

public class Hero extends FatherObjects implements IDrawable, IMovable {

    public int[] cord_x = { 400, 430, 370 };
    public int[] cord_y = { 540, 600, 600 };

    private int lineHeight = (int) (0.66*600);
    public int[] cord_x_line = { 0, 800, 800,0 };
    public int[] cord_y_line = { lineHeight, lineHeight, lineHeight+5, lineHeight+5  };

    public Hero() {
        super.setPointsX(cord_x);
        super.setPointsY(cord_y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);
        graphics.setColor(Color.RED);
        graphics.fillPolygon(cord_x_line, cord_y_line, 4);


    }

    @Override
    public void moveUp(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(int variable) {
        // Restar
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] - variable;

        }
    }

    @Override
    public void moveRight(int variable) {
        // Sumar
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] + variable;

        }

    }

    @Override
    public void draw(Graphics graphics, IDrawable drawable) {
        // TODO Auto-generated method stub

    }
}