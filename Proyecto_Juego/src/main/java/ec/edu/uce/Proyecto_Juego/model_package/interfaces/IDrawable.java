package ec.edu.uce.Proyecto_Juego.model_package.interfaces;

import java.awt.*;

public interface IDrawable {
    public void draw(Graphics graphics);

    public void draw(Graphics graphics, IDrawable drawable);
}
