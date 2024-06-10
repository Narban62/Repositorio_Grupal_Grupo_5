package ec.edu.uce.Proyecto_Juego.view_package;

import ec.edu.uce.Proyecto_Juego.controller_package.Container;
import ec.edu.uce.Proyecto_Juego.controller_package.ServerConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;

public class GameFrame extends JFrame implements KeyListener {

    private final JPanel cards;
    private final Container container = new Container();
    private int counter = 0;
    private final int shootInterval = 1000;
    private Timer timer;
    private Image backgroundImage;
    private Image backgroundImage2;
    private Image backgroundImage3;
    private Image backgroundImage4;
    private String playerName = "";

    private boolean gamePaused = false;


    public GameFrame() {
        super("Juego Galaga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        cards = new JPanel(new CardLayout());
        addWelcomePanel();
        addGamePanel();
        addPausePanel();
        addGameOverPanel();
        addYouWinPanel();

        setContentPane(cards);
        addKeyListener(this);
        setVisible(true);
    }

    private void showWelcomePanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "WelcomePanel");
    }

    private void showGameOverPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "GameOverPanel");
    }

    public void switchToGamePanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "GamePanel");
        requestFocusInWindow();
        timer.start();
    }

    private void showYouWinPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "YouWinPanel");
    }

    private void addYouWinPanel() {

            try {
                backgroundImage3 = ImageIO.read(getClass().getResource("/images/win.jpg"));
                System.out.println("Imagen cargada directamente desde el sistema de archivos: " + (backgroundImage3 != null));
            } catch (IOException e) {
                System.err.println("Error al leer la imagen desde el sistema de archivos: " + e.getMessage());
                e.printStackTrace();
            }


        JPanel youWinPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage3 != null) {
                    g.drawImage(backgroundImage3, 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    System.err.println("La imagen de fondo es null");
                }
            }
        };
        youWinPanel.setLayout(null);

        JLabel winLabel = new JLabel("¡Has ganado!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setBounds(200, 50, 300, 30);
        youWinPanel.add(winLabel);

        JButton replayButton = new JButton("Volver a jugar");
        replayButton.setFont(new Font("Arial", Font.PLAIN, 16));
        replayButton.setBounds(250, 400, 200, 30);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWelcomePanel();
            }
        });
        youWinPanel.add(replayButton);

        cards.add(youWinPanel, "YouWinPanel");
        youWinPanel.setVisible(false);
    }


    private void addWelcomePanel() {

            try {
                backgroundImage = ImageIO.read(getClass().getResource("/images/pxfuel.jpg"));
                System.out.println("Imagen cargada directamente desde el sistema de archivos: " + (backgroundImage != null));
            } catch (IOException e) {
                System.err.println("Error al leer la imagen desde el sistema de archivos: " + e.getMessage());
                e.printStackTrace();
            }


        JPanel welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    System.err.println("La imagen de fondo es null");
                }
            }
        };
        welcomePanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("¡Bienvenido!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(250, 50, 300, 30);  // Ajusta la posición y el tamaño del JLabel
        welcomePanel.add(welcomeLabel);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setBounds(250, 350, 300, 30);
        welcomePanel.add(nameField);

        JButton newGameButton = new JButton("Juego Nuevo");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 16));
        newGameButton.setBounds(250, 400, 300, 30);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                gamePaused = false;
                if (!playerName.isEmpty()) {
                    container.registerHero(playerName,1,0,100);
                    System.out.println("Nuevo juego iniciado por: " + playerName);
                    container.resetGame();
                    switchToGamePanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese su nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        welcomePanel.add(newGameButton);

        JButton loadGameButton = new JButton("Cargar Juego");
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loadGameButton.setBounds(250, 450, 300, 30);
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                if (!playerName.isEmpty()) {
                    System.out.println("Juego Cargado por: " + playerName);
                    switchToGamePanel();
                    container.loadGameState(playerName);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese su nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }


            }
        });
        welcomePanel.add(loadGameButton);

        cards.add(welcomePanel, "WelcomePanel");
    }

    private void addGamePanel() {
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                container.draw(g);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("Jugador: " + playerName, 10, 20); // Mostrar el nombre del jugador
                g.drawString("Puntuación: " + container.getScoreFromHero(), 10, 40); // Mostrar la puntuación
                g.drawString("Vida: " + container.getLifeFromHero(), 10, 70); // Mostrar las vidas
            }
        };
        gamePanel.setBackground(Color.black);
        gamePanel.setLayout(null);
        cards.add(gamePanel, "GamePanel");


        JButton pauseButton = new JButton("Pausa (P)");
        pauseButton.setFont(new Font("Arial", Font.PLAIN, 16));
        pauseButton.setBounds(10, 500, 120, 30);  // Ajusta la posición y el tamaño del JButton
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePaused = !gamePaused;
                showPausePanel(gamePaused);
            }
        });
        gamePanel.add(pauseButton);

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gamePaused) {
                    container.moveDown(1);
                    container.moveUp(10);
                    container.checkCollisions();
                    counter++;
                    if (counter >= shootInterval / 30) {
                        container.createShootOpponents();
                        counter = 0;
                    }
                    gamePanel.repaint();
                    if (container.ckeckColissionLine()) {
                        showGameOverPanel();
                        timer.stop();
                    }
                    if (container.getLifeFromHero() <= 0) {
                        showGameOverPanel();
                        timer.stop();
                    }
                    if (container.areAllLevel3OpponentsEliminated()) {
                        showYouWinPanel();
                        timer.stop();
                    }
                }
            }
        });
    }


    private void addGameOverPanel() {

            try {
                backgroundImage2 = ImageIO.read(getClass().getResource("/images/died.jpg"));
                System.out.println("Imagen cargada directamente desde el sistema de archivos: " + (backgroundImage != null));
            } catch (IOException e) {
                System.err.println("Error al leer la imagen desde el sistema de archivos: " + e.getMessage());
                e.printStackTrace();
            }


        JPanel gameOverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage2 != null) {
                    g.drawImage(backgroundImage2, 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    System.err.println("La imagen de fondo es null");
                }
            }
        };

        gameOverPanel.setLayout(null);


        JButton replayButton = new JButton("Volver a jugar");
        replayButton.setFont(new Font("Arial", Font.PLAIN, 16));
        replayButton.setBounds(250, 400, 200, 30);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWelcomePanel();
            }
        });
        gameOverPanel.add(replayButton);

        cards.add(gameOverPanel, "GameOverPanel");
        gameOverPanel.setVisible(false); // Inicialmente oculto
    }

    private void addPausePanel() {

        try {
            backgroundImage4 = ImageIO.read(getClass().getResource("/images/pause.jpg"));
            System.out.println("Imagen cargada directamente desde el sistema de archivos: " + (backgroundImage != null));
        } catch (IOException e) {
            System.err.println("Error al leer la imagen desde el sistema de archivos: " + e.getMessage());
            e.printStackTrace();
        }

        JPanel pausePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage4 != null) {
                    g.drawImage(backgroundImage4, 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    System.err.println("La imagen de fondo es null");
                }
            }
        };
        pausePanel.setLayout(null);



        JLabel pauseLabel = new JLabel("Juego Pausado", SwingConstants.CENTER);
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pauseLabel.setBounds(250, 50, 300, 30);
        pausePanel.add(pauseLabel);

        JButton saveButton = new JButton("Guardar Partida");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 16));
        saveButton.setBounds(300, 250, 200, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerConnection serverConnection = new ServerConnection();
                serverConnection.saveGame(playerName, container.getLevel()-1, container.getScoreFromHero(), container.getLifeFromHero());
                JOptionPane.showMessageDialog(null, "Partida guardada.", "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
                showWelcomePanel();
            }
        });
        pausePanel.add(saveButton);

        JButton returnButton = new JButton("Volver");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnButton.setBounds(300, 300, 200, 30);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPausePanel(false);
                gamePaused = !gamePaused;
                GameFrame.this.requestFocusInWindow();
            }
        });
        pausePanel.add(returnButton);

        cards.add(pausePanel, "PausePanel");
        pausePanel.setVisible(false);
    }

    private void showPausePanel(boolean show) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (show) {
            cl.show(cards, "PausePanel");
        } else {
            cl.show(cards, "GamePanel");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
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
        } else if (tecla == KeyEvent.VK_P) {
            gamePaused = !gamePaused;
            showPausePanel(gamePaused);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
