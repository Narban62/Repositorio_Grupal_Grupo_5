package ec.edu.uce.Proyecto_Juego.model_package.objects;

import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IDrawable;
import ec.edu.uce.Proyecto_Juego.model_package.interfaces.IMovable;

import java.awt.*;

public class Hero extends FaherObjects implements IDrawable, IMovable {

    public int[] cord_x = { 400, 430, 370 };
    public int[] cord_y = { 540, 600, 600 };

    private int lineHeigth= (int) (0.66*600);

    public int[] cord_x_linea = { 0, 800, 800, 0 };
    public int[] cord_y_linea = { lineHeigth, lineHeigth, lineHeigth+5, lineHeigth+5 };

    public int[] cord_X_Life = { 0, 200, 200, 0 };
    public int[] cord_Y_Life = { 50, 50, 80, 80 };

    private int life = 100;
    private int score = 0;
    private String name;
    private String levelName;

    public Hero() {
        super.setPointsX(cord_x);
        super.setPointsY(cord_y);
        super.setLife(life);

    }

    @Override
    public void draw(Graphics graphics) {

        if (life>0){
            //dibujar el personaje
            graphics.setColor(Color.WHITE);
            graphics.fillPolygon(cord_x, cord_y, 3);
        }

        if (levelName!= null){
            //nombre nivel
            graphics.setColor(Color.white);
            Font font_Level = new Font("Arial", Font.BOLD, 40);
            graphics.setFont(font_Level);
            graphics.drawString(levelName, 400-(font_Level.getSize()/2), 75);

        }

        //dibujar la linea
        graphics.setColor(Color.red);
        graphics.fillPolygon(cord_x_linea, cord_y_linea, 4);

        // Dibujar la vida
        graphics.setColor(Color.red);
        graphics.fillPolygon(cord_X_Life, cord_Y_Life, 4);
        graphics.setColor(Color.red);
        Font font = new Font("Arial", Font.BOLD, 25);
        graphics.setFont(font);
        graphics.drawString( String.valueOf(life), cord_X_Life[3]+10, cord_Y_Life[3]-5);

        //dibuja el score en pantalla
//        graphics.setColor(Color.white);
//        Font font_life = new Font("Arial", Font.BOLD, 30);
//        graphics.setFont(font_life);
//        graphics.drawString("Score: " + score, 625, 75);
        //dibuja el Nombre en pantalla


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
    public void reduceLife(int variable) {

        if (life > 0){
            life -= variable;
            System.out.println(life);
            for (int i = 1; i < 3; i++) {
                cord_X_Life[i] -= variable*2;
            }
        }
    }
    public void addScore(int variable) {
        score += variable;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {

        this.life = life;
    }

    public String getLevelName() {

        return levelName;
    }

    public void setLevelName(String levelName) {

        this.levelName = levelName;
    }

    public int getLineHeigth() {

        return lineHeigth;
    }

    public void setLineHeigth(int lineHeigth) {

        this.lineHeigth = lineHeigth;
    }

    public void setScore(int score) {
        this.score = score;
    }
}