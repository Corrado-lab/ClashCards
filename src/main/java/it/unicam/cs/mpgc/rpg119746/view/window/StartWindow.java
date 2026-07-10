package it.unicam.cs.mpgc.rpg119746.view.window;

import it.unicam.cs.mpgc.rpg119746.controller.GameController;
import it.unicam.cs.mpgc.rpg119746.persistence.GameRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartWindow extends JFrame {

    private final GameRepository gameRepository;
    private final JLabel titleLabel;
    private final JLabel subtitleLabel;
    private final JTextField playerNameField;
    private final JButton newGameButton;
    private final JButton loadGameButton;
    private final JButton exitButton;

    public StartWindow() {
        this.gameRepository = new GameRepository();

        this.titleLabel = new JLabel("ClashCards");
        this.subtitleLabel = new JLabel("Schiera le tue carte e sconfiggi i mostri");
        this.playerNameField = new JTextField();

        this.newGameButton = new JButton("Nuova Partita");
        this.loadGameButton = new JButton("Carica Partita");
        this.exitButton = new JButton("Esci");

        this.configureWindow();
        this.configureComponents();
        this.buildLayout();
        this.configureActions();

        setVisible(true);
    }

    private void configureWindow() {
        setTitle("ClashCards - Menu Iniziale");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(241, 245, 249));
    }

    private void configureComponents() {

        this.titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        this.titleLabel.setForeground(new Color(30, 58, 138));
        this.titleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        this.subtitleLabel.setForeground(new Color(100, 116, 139));
        this.subtitleLabel.setHorizontalAlignment(JLabel.CENTER);

        this.playerNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.playerNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        this.playerNameField.setPreferredSize(new Dimension(220, 40));

        Font mainButtonFont = new Font("Segoe UI", Font.BOLD, 15);
        Color primaryBlue = new Color(37, 99, 235);
        Color grayButton = new Color(71, 85, 105);
        Dimension buttonSize = new Dimension(180, 50);

        configureMainButton(this.newGameButton, mainButtonFont, primaryBlue, buttonSize);
        configureMainButton(this.loadGameButton, mainButtonFont, grayButton, buttonSize);

        this.exitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.exitButton.setForeground(Color.BLACK);
        this.exitButton.setBorderPainted(false);
        this.exitButton.setContentAreaFilled(false);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(new Color(100, 116, 139));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.BLACK);
            }
        });
    }

    private void configureMainButton(JButton button, Font font, Color bgColor, Dimension size) {

        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(size);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }

    private void buildLayout() {

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        this.titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        topPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        topPanel.add(this.titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(this.subtitleLabel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Nome del Giocatore");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nameLabel.setForeground(new Color(51, 65, 85));
        centerPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 8, 40, 8);
        centerPanel.add(this.playerNameField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        actionButtonsPanel.setOpaque(false);
        actionButtonsPanel.add(this.newGameButton);
        actionButtonsPanel.add(this.loadGameButton);
        centerPanel.add(actionButtonsPanel, gbc);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(this.exitButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 20));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void configureActions() {
        this.newGameButton.addActionListener(e -> this.startNewGame());
        this.loadGameButton.addActionListener(e -> this.loadGame());
        this.exitButton.addActionListener(e -> System.exit(0));
    }

    private void startNewGame() {
        String playerName = this.playerNameField.getText().trim();
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Per favore, inserisci un nome prima di iniziare il duello!",
                    "Nome Mancante",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        GameController gameController = new GameController(playerName);
        JOptionPane.showMessageDialog(this, "Battaglia pronta per " + playerName + "!\nI duelli a carte stanno per iniziare.");
        new MainWindow(gameController);
        this.dispose();
    }

    private void loadGame() {
        try {
            GameController gameController = this.gameRepository.loadGame("savegame.json");
            JOptionPane.showMessageDialog(this, "Partita caricata! Bentornato nel duello, " + gameController.getPlayer().getName());
            new MainWindow(gameController);
            this.dispose();
        } catch (IllegalStateException exception) {
            JOptionPane.showMessageDialog(this,
                    "Nessun file di salvataggio trovato o file corrotto.",
                    "Errore di Caricamento",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
