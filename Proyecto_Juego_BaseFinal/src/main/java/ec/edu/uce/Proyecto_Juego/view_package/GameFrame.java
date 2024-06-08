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
    private int level = 1;
    private JPanel contentPane;
    private Container container=new Container();
    private int counter = 0;
    private int shootInterval = 1000; // intervalo para disparar las balas en milisegundos

    public GameFrame () {
        super("Juego Galaga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);

        container = new Container();
        contentPane = new JPanel();

        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        addKeyListener(this);

        Timer timer = new Timer(30, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                container.moveDown(1);
                counter++;
                if (counter >= shootInterval / 30) { // 30 es el intervalo de tiempo del temporizador principal en milisegundos
                    container.createShootOpponents();
                    counter = 0; // Reiniciar el contador después de disparar
                }
//                if (container.getHero().reduceLife(0) {
//                    JOptionPane.showMessageDialog(null, "Game Over");
//                    // Reinicia el juego en lugar de terminarlo
//
//                }

                if (level == 1) {
                    container.checkCollisions();
                } else if (level == 2){
                    container.checkCollisionsLevel2();
                }

                repaint();

            }

        });
        timer.start();

        setVisible(true);

    }

    public void paint (Graphics graphics) {
        // Crear una imagen fuera de la pantalla
        Image offscreenImage = createImage(getWidth(), getHeight());
        // Obtener el objeto Graphics de la imagen fuera de la pantalla
        Graphics offscreenGraphics = offscreenImage.getGraphics();

        // Llamar a super.paint() con el objeto Graphics fuera de la pantalla
        super.paint(offscreenGraphics);
        // Dibujar en el objeto Graphics fuera de la pantalla
        container.draw(offscreenGraphics);
        container.moveUp(40);

        // Copiar la imagen fuera de la pantalla en la pantalla
        graphics.drawImage(offscreenImage, 0, 0, this);

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


    //prueba pescado
    @Override
    public void keyReleased(KeyEvent e) {


    }


}