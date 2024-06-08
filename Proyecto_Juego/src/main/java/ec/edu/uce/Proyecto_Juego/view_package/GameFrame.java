package ec.edu.uce.Proyecto_Juego.view_package;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ec.edu.uce.Proyecto_Juego.controller_package.Container;


public class GameFrame extends JFrame implements KeyListener{

    //private static final long serialVersionUID =1L;
    private JPanel contentPane;
    private Container container=new Container();

    private int counter =0;
    private int shootInterval;

    public GameFrame () {
        super("Juego Galaga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);

        container = new Container();
        contentPane = new JPanel();

        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        addKeyListener(this);

        Timer timer = new Timer(500, new ActionListener() {
//            Timer timer = new Timer(30, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveDown(1);
//                if (counter >= shootInterval/30){
                if (counter >= shootInterval/500){
                    container.crateShootOpponents();
                    counter =0;
                }
                repaint();

            }

        });
        timer.start();

        setVisible(true);

    }
    public void paint (Graphics graphics) {
        super.paint(graphics);
        container.draw(graphics);
        container.moveUp(40);

        container.checkCollisions();

    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_SPACE) {
            container.createShootHero();
        } else if (tecla == KeyEvent.VK_LEFT) {
            container.moveLeft(10);
        } else if (tecla == KeyEvent.VK_RIGHT) {
            container.moveRight(10);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        setFocusable(true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_N: {
                container.moveUp(10);;
                break;
            }

        }
        repaint();


    }


}