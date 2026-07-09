package it.unicam.cs.mpgc.rpg119746.view.window;

import it.unicam.cs.mpgc.rpg119746.controller.GameController;
import it.unicam.cs.mpgc.rpg119746.model.characters.Player;
import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119746.persistence.GameRepository;
import it.unicam.cs.mpgc.rpg119746.view.panels.EnemyPanel;
import it.unicam.cs.mpgc.rpg119746.view.panels.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final GameController gameController;
    private final GameRepository gameRepository;

    private final JLabel statusMessageLabel;

    private final JButton attackButton;
    private final JButton abilityButton;
    private final JButton saveButton;
    private final JButton loadButton;

    private final PlayerPanel playerPanel;
    private final EnemyPanel enemyPanel;

    public MainWindow(GameController gameController) {
        this.gameController = gameController;
        this.gameRepository = new GameRepository();

        if (this.gameController.getCurrentBattle() == null) {
            this.gameController.startNextBattle();
        }

        this.statusMessageLabel = new JLabel("Il duello ha inizio! Fai la tua mossa.");
        this.attackButton = new JButton("Attacca!");
        this.abilityButton = new JButton("Usa Abilità");
        this.saveButton = new JButton("Salva Partita");
        this.loadButton = new JButton("Carica Partita");

        this.playerPanel = new PlayerPanel();
        this.enemyPanel = new EnemyPanel();

        this.configureWindow();
        this.configureComponents();
        this.buildLayout();
        this.configureActions();

        this.updateView();

        setVisible(true);
    }

    private void configureWindow() {
        setTitle("ClashCards - Campo di Battaglia");
        setSize(760, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(241, 245, 249));
    }

    private void configureComponents() {
        this.statusMessageLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.statusMessageLabel.setForeground(new Color(30, 58, 138));
        this.statusMessageLabel.setHorizontalAlignment(JLabel.CENTER);

        Font actionBtnFont = new Font("Segoe UI", Font.BOLD, 14);
        Dimension actionBtnSize = new Dimension(140, 40);
        configureCustomButton(this.attackButton, actionBtnFont, actionBtnSize);
        configureCustomButton(this.abilityButton, actionBtnFont, actionBtnSize);

        Font utilFont = new Font("Segoe UI", Font.BOLD, 12);
        this.saveButton.setFont(utilFont);
        this.loadButton.setFont(utilFont);
        this.saveButton.setPreferredSize(new Dimension(140, 32));
        this.loadButton.setPreferredSize(new Dimension(140, 32));
    }

    private void configureCustomButton(JButton button, Font font, Dimension size) {
        button.setFont(font);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setPreferredSize(size);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void buildLayout() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        topPanel.add(this.statusMessageLabel);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(topPanel, BorderLayout.NORTH);

        JPanel gameTablePanel = new JPanel();
        gameTablePanel.setLayout(new BoxLayout(gameTablePanel, BoxLayout.Y_AXIS));
        gameTablePanel.setOpaque(false);
        gameTablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.enemyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameTablePanel.add(Box.createVerticalGlue());
        gameTablePanel.add(this.enemyPanel);
        gameTablePanel.add(Box.createRigidArea(new Dimension(0, 35)));
        gameTablePanel.add(this.playerPanel);
        gameTablePanel.add(Box.createVerticalGlue());

        add(gameTablePanel, BorderLayout.CENTER);

        JPanel sideCommandsPanel = new JPanel(new GridBagLayout());
        sideCommandsPanel.setBackground(new Color(226, 232, 240));
        sideCommandsPanel.setBorder(BorderFactory.createEmptyBorder(25, 15, 25, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; sideCommandsPanel.add(this.attackButton, gbc);
        gbc.gridy = 1; sideCommandsPanel.add(this.abilityButton, gbc);

        gbc.gridy = 2; gbc.weighty = 1.0;
        sideCommandsPanel.add(Box.createGlue(), gbc);

        gbc.weighty = 0.0;
        gbc.gridy = 3; sideCommandsPanel.add(this.saveButton, gbc);
        gbc.gridy = 4; sideCommandsPanel.add(this.loadButton, gbc);

        add(sideCommandsPanel, BorderLayout.EAST);
    }

    private void configureActions() {
        this.attackButton.addActionListener(e -> this.handleAttack());
        this.abilityButton.addActionListener(e -> this.handleUseAbility());
        this.saveButton.addActionListener(e -> this.saveGame());
        this.loadButton.addActionListener(e -> this.loadGame());
    }

    private void updateView() {
        Player player = this.gameController.getPlayer();
        var battle = this.gameController.getCurrentBattle();

        String pName = player.getName();
        int pCurrentHp = player.getHealthPoints();
        int pMaxHp = player.getMaxHealthPoints();

        this.playerPanel.getPlayerLevelLabel().setText("LV: " + player.getLevel());
        this.playerPanel.getPlayerNameLabel().setText(pName.toUpperCase());
        this.playerPanel.getPlayerExpLabel().setText("EXP: " + player.getCurrentExp() + "/" + player.getExpToNextLevel());

        this.playerPanel.getPlayerHealthBar().setMaximum(pMaxHp);
        this.playerPanel.getPlayerHealthBar().setValue(pCurrentHp);
        this.playerPanel.getPlayerHealthBar().setString(pCurrentHp + " / " + pMaxHp + " HP");

        this.playerPanel.getPlayerAttackLabel().setText("<html><b>ATK:</b> " + player.getBaseDamage() + "</html>");

        if (battle != null) {
            Enemy enemy = battle.getEnemy();
            String eName = enemy.getName();
            int eCurrentHp = enemy.getHealthPoints();
            int eMaxHp = enemy.getMaxHealthPoints();

            this.enemyPanel.getEnemyNameLabel().setText(eName.toUpperCase());

            this.enemyPanel.getEnemyHealthBar().setMaximum(eMaxHp);
            this.enemyPanel.getEnemyHealthBar().setValue(eCurrentHp);
            this.enemyPanel.getEnemyHealthBar().setString(eCurrentHp + " / " + eMaxHp + " HP");

            this.enemyPanel.getEnemyStageLabel().setText("STADIO " + this.gameController.getCurrentStage());
            this.enemyPanel.getEnemyAttackLabel().setText("<html><b>ATK:</b> " + enemy.getBaseDamage() + "</html>");

            boolean alive = player.isAlive() && enemy.isAlive();
            this.attackButton.setEnabled(alive);
            this.abilityButton.setEnabled(alive);
        } else {
            this.enemyPanel.getEnemyNameLabel().setText("VUOTO");
            this.attackButton.setEnabled(false);
            this.abilityButton.setEnabled(false);
        }
    }

    private void handleAttack() {
        var battle = this.gameController.getCurrentBattle();
        if (battle == null) return;

        battle.executePlayerAttack();
        this.updateView();

        if (battle.isBattleOver()) {
            this.gameController.handleBattleEnd();

            if (battle.isPlayerVictorious()) {
                JOptionPane.showMessageDialog(this, "Vittoria! Guadagni " + battle.getEnemy().getExpReward() + " EXP.", "Vittoria", JOptionPane.INFORMATION_MESSAGE);

                if (!this.gameController.isGameWon()) {
                    this.gameController.startNextBattle();
                    this.statusMessageLabel.setText("Il duello ha inizio! Fai la tua mossa.");
                    this.updateView();
                } else {
                    this.statusMessageLabel.setText("Hai completato l'ultimo stadio!");
                    JOptionPane.showMessageDialog(this, "Hai vinto il gioco! Congratulazioni!", "Vittoria Totale", JOptionPane.INFORMATION_MESSAGE);

                    this.gameRepository.deleteSaveGame("savegame.json");
                    this.dispose();
                    new StartWindow().setVisible(true);
                }
            } else {
                this.statusMessageLabel.setText("Sei stato sconfitto!");
                JOptionPane.showMessageDialog(this, "Game Over! Il Warden è caduto.", "Sconfitta", JOptionPane.ERROR_MESSAGE);

                this.gameRepository.deleteSaveGame("savegame.json");
                this.dispose();
                new StartWindow().setVisible(true);
            }
        } else {
            this.statusMessageLabel.setText("Scambio di colpi avvenuto!");
        }
    }

    private void handleUseAbility() {
        var battle = this.gameController.getCurrentBattle();
        if (battle == null) return;

        try {
            battle.executePlayerAbility();
            this.statusMessageLabel.setText("Abilità attivata! Il prossimo attacco raddoppia i danni.");
            this.abilityButton.setEnabled(false);
            this.updateView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Attenzione", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveGame() {
        try {
            this.gameRepository.saveGame(this.gameController, "savegame.json");
            this.statusMessageLabel.setText("Partita salvata.");
            JOptionPane.showMessageDialog(this, "Salvataggio completato!", "Salva", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exception) {
            this.statusMessageLabel.setText("Impossibile salvare il gioco.");
        }
    }

    private void loadGame() {
        try {
            GameController loadedController = this.gameRepository.loadGame("savegame.json");
            new MainWindow(loadedController);
            this.dispose();
        } catch (Exception exception) {
            this.statusMessageLabel.setText("Errore durante il caricamento.");
            JOptionPane.showMessageDialog(this, "Nessun salvataggio valido trovato.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}