package it.unicam.cs.mpgc.rpg119746.view.panels;

import javax.swing.*;
import java.awt.*;

public class EnemyPanel extends JPanel {

    private final JLabel enemyNameLabel;
    private final JProgressBar enemyHealthBar;
    private final JLabel enemyStageLabel;
    private final JLabel enemyAttackLabel;

    public EnemyPanel() {
        Dimension cardSize = new Dimension(240, 310);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(37, 99, 235), 3, true));
        setPreferredSize(cardSize);
        setMinimumSize(cardSize);
        setMaximumSize(cardSize);

        this.enemyNameLabel = new JLabel();
        this.enemyHealthBar = new JProgressBar();
        this.enemyStageLabel = new JLabel();
        this.enemyAttackLabel = new JLabel();

        this.configureComponents();
        this.setupLayout();
    }

    private void configureComponents() {
        Font bigNameFont = new Font("Segoe UI", Font.BOLD, 20);
        Font detailsFont = new Font("Segoe UI", Font.PLAIN, 14);

        this.enemyNameLabel.setFont(bigNameFont);
        this.enemyNameLabel.setForeground(Color.BLACK);
        this.enemyStageLabel.setFont(detailsFont);
        this.enemyAttackLabel.setFont(detailsFont);

        this.enemyHealthBar.setForeground(new Color(37, 99, 235));
        this.enemyHealthBar.setBackground(new Color(226, 232, 240));
        this.enemyHealthBar.setStringPainted(true);
        this.enemyHealthBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.enemyHealthBar.setPreferredSize(new Dimension(200, 26));
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;

        c.gridy = 0;
        c.insets = new Insets(10, 5, 15, 5);
        c.anchor = GridBagConstraints.CENTER;
        add(this.enemyNameLabel, c);

        c.gridy = 1;
        c.insets = new Insets(10, 10, 20, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(this.enemyHealthBar, c);

        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 8, 5);
        add(this.enemyStageLabel, c);

        c.gridy = 3;
        c.insets = new Insets(4, 5, 25, 5);
        add(this.enemyAttackLabel, c);
    }

    public JLabel getEnemyNameLabel() { return enemyNameLabel; }
    public JProgressBar getEnemyHealthBar() { return enemyHealthBar; }
    public JLabel getEnemyStageLabel() { return enemyStageLabel; }
    public JLabel getEnemyAttackLabel() { return enemyAttackLabel; }
}
