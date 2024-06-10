package game_package;

import model_package.Bullet;
import model_package.Hero;
import model_package.Opponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;


public class GameFrame extends JFrame {
	private Hero hero;
	private Opponents enemies;
	private Container container;
	private final int GAME_OVER_LINE_Y = 400;

	public GameFrame() {

	}

	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("UNIVERSIDAD CENTRAL DEL ECUADOR");
		setResizable(false);
		setLocation(290, 120);
		setSize(800, 600);

		String name = JOptionPane.showInputDialog("Ingrese nombre del usuario:");
		hero = new Hero(390, 440, 100, name);
		enemies = new Opponents(5, 800, 600);

		// Crear la instancia de Container
		container = new Container(hero, new Bullet(), List.of(enemies), new Random());

		JPanel gamePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}
		};

		gamePanel.setBackground(Color.BLACK);
		gamePanel.setFocusable(true);

		gamePanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
					case KeyEvent.VK_UP:
						container.move(hero, "UP");
						break;
					case KeyEvent.VK_DOWN:
						container.move(hero, "DOWN");
						break;
					case KeyEvent.VK_LEFT:
						container.move(hero, "LEFT");
						break;
					case KeyEvent.VK_RIGHT:
						container.move(hero, "RIGHT");
						break;
					case KeyEvent.VK_SPACE:
						container.shoot(hero);
						break;
				}
				gamePanel.repaint();
			}
		});

		Timer timer = new Timer(1000 / 60, e -> {
			updateGame();
			gamePanel.repaint();
		});
		timer.start();

		setContentPane(gamePanel);
		setVisible(true);
		gamePanel.requestFocusInWindow();
	}

	private void updateGame() {
		hero.update();
		container.move(enemies, "LEFT");
		enemies.updateBullet();

		Iterator<Bullet> bulletIterator = hero.getBullets().iterator();
		Iterator<Bullet> bulletIterator2 = enemies.getBullets().iterator();

		while (bulletIterator.hasNext()) {
			Bullet bullet = bulletIterator.next();
			if (enemies.checkCollision(bullet)) {
				bulletIterator.remove();
			}
		}

		while (bulletIterator2.hasNext()) {
			Bullet bullet = bulletIterator2.next();
			if (hero.checkCollision(bullet)) {
				hero.reduceHealth();
				bulletIterator2.remove();
				if (hero.getHealth() == 0) {
					System.out.println("el heroe ha muerto");
				}
			}
			if (bullet.getY() >= getHeight()) {
				bulletIterator2.remove();
			}
		}

		if (!hero.isAlive() || hero.getY() < GAME_OVER_LINE_Y) {
			JOptionPane.showMessageDialog(null, "GAME OVER");
			System.exit(0);
		}

		if (enemies.noMoreEnemies()) {
			JOptionPane.showMessageDialog(null, "YOU WIN!");
			System.exit(0);
		}
	}

	private void drawGame(Graphics g) {
		container.draw(hero, g);
		container.draw(enemies, g);
		drawScoreAndHealth(g);

		for (Bullet bullet : hero.getBullets()) {
			container.draw(bullet, g);
		}

		for (Bullet bullet : enemies.getBullets()) {
			container.draw(bullet, g);
		}

		g.setColor(Color.RED);
	}

	private void drawScoreAndHealth(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString("Score: " + enemies.getScore(), 670, 20);
		g.drawString(hero.getName(), 0, 20);

		g.setColor(Color.RED);
		g.fillRect(2, 30, hero.getHealth(), 15);

		int yLinea = (int) (getHeight() * 0.66);
		g.setColor(Color.RED);
		g.drawLine(0, yLinea, getWidth(), yLinea);
	}

}
