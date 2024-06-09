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

        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                container.moveDown(1);

                counter++;
                if (counter >= shootInterval / 30) { // 30 es el intervalo de tiempo del temporizador principal en milisegundos
                    container.createShootOpponents();
                    counter = 0; // Reiniciar el contador después de disparar
                }

                repaint();

                if (container.ckeckColissionLine()) {
                    // Mostrar ventana de Game Over
                    JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    // Cerrar el juego
                    System.exit(0);
                }

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
        container.ckeckColissionLine();

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
            container.moveLeft(30);
        } else if (tecla == KeyEvent.VK_RIGHT) {
            container.moveRight(30);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }


}