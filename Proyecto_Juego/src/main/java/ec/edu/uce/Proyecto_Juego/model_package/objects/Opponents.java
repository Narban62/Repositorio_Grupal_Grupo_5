package ec.edu.uce.Proyecto_Juego.model_package.objects;

import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IDrawable;
import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IMovable;

import java.awt.*;

public class Opponents extends FatherObjects implements IDrawable, IMovable {

    int[] cord_x = new int[5];
    int[] cord_y = new int[5];

    public Opponents() {
        super.setPointsX(cord_x);
        super.setPointsY(cord_y);
    }

    public Opponents(int randomX, int randomY) {
        cord_x[0] = randomX;
        cord_x[1] = randomX + 100;
        cord_x[2] = randomX + 100;
        cord_x[3] = randomX + 50;
        cord_x[4] = randomX;

        cord_y[0] = randomY;
        cord_y[1] = randomY;
        cord_y[2] = randomY + 50;
        cord_y[3] = randomY + 25;
        cord_y[4] = randomY + 50;

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(cord_x, cord_y, 5);

    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {
        for (int i = 0; i < cord_y.length; i++) {
            cord_y[i] = cord_y[i] + variable;

        }

    }

    @Override
    public void moveLeft(int variable) {

    }

    @Override
    public void moveRight(int variable) {

    }

    @Override
    public void draw(Graphics graphics, IDrawable drawable) {
        // TODO Auto-generated method stub

    }

    public Rectangle getBounds() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < cord_x.length; i++) {
            minX = Math.min(minX, cord_x[i]);
            minY = Math.min(minY, cord_y[i]);
            maxX = Math.max(maxX, cord_x[i]);
            maxY = Math.max(maxY, cord_y[i]);
        }

        int width = maxX - minX;
        int height = maxY - minY;

        return new Rectangle(minX, minY, width, height);
    }

}