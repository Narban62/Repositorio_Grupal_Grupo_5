package game_package;

import static java.awt.Font.PLAIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelGame extends JPanel {

	private Hero hero;
    private Villain enemies;
    private final int GAME_OVER_LINE_Y = 400;

    public PanelGame() {
        setBackground(Color.BLACK);
        setFocusable(true);
        
        String name = JOptionPane.showInputDialog("Ingrese nombre del usuario:");
        hero = new Hero(390, 440, 200, name);
        enemies = new Villain(8, 800, 600);
        
    
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        hero.movements("UP");
                        break;
                    case KeyEvent.VK_DOWN:
                        hero.movements("DOWN");
                        break;
                    case KeyEvent.VK_LEFT:
                        hero.movements("LEFT");
                        break;
                    case KeyEvent.VK_RIGHT:
                        hero.movements("RIGHT");
                        break;
                    case KeyEvent.VK_SPACE:
                        hero.shoot(); // El jugador dispara 
                        break;
                }
            }
        });

        // Agregar un temporizador
        Timer timer = new Timer(1000 / 60, e -> {
            updateGame(); // Actualizar el estado del juego
            repaint(); // Volver a dibujar el panel
        });
        timer.start();
    }

    private void updateGame() {
        hero.update(); // Actualizar las balas del jugador
        enemies.movements("LEFT"); // Mover a los enemigos

        // Verificar las colisiones entre las balas del jugador y los enemigos
        Iterator<Bullet> bulletIterator = hero.getBullets().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (enemies.checkCollision(bullet)) {
                bulletIterator.remove(); // Eliminar la bala
            }
        }

     // Verificar si el jugador ha perdido todas las vidas
        if (!hero.isAlive() || hero.getY() < GAME_OVER_LINE_Y) {
            JOptionPane.showMessageDialog(null, "GAME OVER");
            System.exit(0);
        }

        // Verificar si todos los enemigos han sido eliminados
        if (enemies.noMoreEnemies()) {
            JOptionPane.showMessageDialog(null, "YOU WIN!");
            System.exit(0);
        }
        
       
    }
    
    private void drawScoreAndHealth(Graphics g) {
        // Dibujar puntaje
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Score: " + enemies.getScore(), 670, 20);
        g.drawString(hero.getName(), 0, 20);

        // Dibujar la barra de vida
        g.setColor(Color.RED);
        g.fillRect(2, 30, hero.getHealth(), 15); // Dibuja la barra de vida en la esquina superior izquierda

        // Dibujar la línea divisoria
        int yLinea = (int) (getHeight() * 0.66);
        g.setColor(Color.RED);
        g.drawLine(0, yLinea, getWidth(), yLinea);

        // Dibujar las balas del jugador
        for (Bullet bullet : hero.getBullets()) {
            bullet.draw(g); // Dibuja cada bala en la lista de balas del jugador
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        hero.draw(g); // Dibujar al jugador
        enemies.draw(g); // Dibujar a los enemigos
        drawScoreAndHealth(g); // Dibujar puntaje, nombre del jugador, barra de vida y línea divisoria

        


     // Dibujar la línea de "Game Over"
        g.setColor(Color.RED);
       
    }
}
        
        


