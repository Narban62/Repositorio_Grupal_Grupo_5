package game_package;

import javax.swing.*;

public class Panel extends JFrame {

	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("UNIVERSIDAD CENTRAL DEL ECUADOR");
		setResizable(false);
		setLocation(290, 120);
		setSize(900, 700);

		String name = JOptionPane.showInputDialog("Ingrese nombre del usuario:");
		Hero hero = new Hero(390, 440, 200, name);
		Opponents enemies = new Opponents(5, 800, 600);
		PanelGame panelG = new PanelGame(hero, enemies);
		add(panelG);

		setVisible(true);
	}

}