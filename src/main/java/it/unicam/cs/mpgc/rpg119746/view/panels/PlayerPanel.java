package it.unicam.cs.mpgc.rpg119746.view.panels;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private final JLabel playerLevelLabel;
    private final JLabel playerNameLabel;
    private final JLabel playerExpLabel;
    private final JProgressBar playerHealthBar;
    private final JLabel playerAttackLabel;

    public PlayerPanel() {
        Dimension cardSize = new Dimension(240, 310);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(37, 99, 235), 3, true));
        setPreferredSize(cardSize);
        setMinimumSize(cardSize);
        setMaximumSize(cardSize);

        this.playerLevelLabel = new JLabel();
        this.playerNameLabel = new JLabel();
        this.playerExpLabel = new JLabel();
        this.playerHealthBar = new JProgressBar();
        this.playerAttackLabel = new JLabel();

        this.configureComponents();
        this.setupLayout();
    }

    private void configureComponents() {
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Font bigNameFont = new Font("Segoe UI", Font.BOLD, 20);
        Font detailsFont = new Font("Segoe UI", Font.PLAIN, 14);

        this.playerLevelLabel.setFont(labelFont);
        this.playerLevelLabel.setForeground(Color.BLACK);
        this.playerNameLabel.setFont(bigNameFont);
        this.playerNameLabel.setForeground(Color.BLACK);
        this.playerExpLabel.setFont(labelFont);
        this.playerExpLabel.setForeground(Color.BLACK);
        this.playerAttackLabel.setFont(detailsFont);

        this.playerHealthBar.setForeground(new Color(37, 99, 235));
        this.playerHealthBar.setBackground(new Color(226, 232, 240));
        this.playerHealthBar.setStringPainted(true);
        this.playerHealthBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.playerHealthBar.setPreferredSize(new Dimension(200, 26));
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 5, 10, 5);
        add(this.playerNameLabel, c);

        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 12, 10, 12);

        c.gridx = 0;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.WEST;
        add(this.playerLevelLabel, c);

        c.gridx = 1;
        c.weightx = 0.0;
        add(Box.createHorizontalStrut(10), c);

        c.gridx = 2;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.EAST;
        add(this.playerExpLabel, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 15, 10);
        add(this.playerHealthBar, c);

        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 25, 5);
        add(this.playerAttackLabel, c);
    }

    public JLabel getPlayerLevelLabel() { return playerLevelLabel; }
    public JLabel getPlayerNameLabel() { return playerNameLabel; }
    public JLabel getPlayerExpLabel() { return playerExpLabel; }
    public JProgressBar getPlayerHealthBar() { return playerHealthBar; }
    public JLabel getPlayerAttackLabel() { return playerAttackLabel; }
}
