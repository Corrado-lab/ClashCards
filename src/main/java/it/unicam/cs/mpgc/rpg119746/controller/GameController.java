package it.unicam.cs.mpgc.rpg119746.controller;

import it.unicam.cs.mpgc.rpg119746.model.characters.Player;
import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119746.persistence.EnemyRepository;

public class GameController {

    private final Player player;
    private final EnemyRepository enemyRepository;
    private BattleController currentBattle;
    private int currentStage;

    public GameController(String playerName) {
        this.player = new Player(playerName, 150, 20);
        this.currentStage = 0;
        this.enemyRepository = new EnemyRepository();
    }

    public GameController(Player player, int currentStage) {
        this.player = player;
        this.currentStage = currentStage;
        this.enemyRepository = new EnemyRepository();
    }

    public Player getPlayer() { return player; }
    public BattleController getCurrentBattle() { return currentBattle; }
    public int getCurrentStage() { return currentStage; }

    public void startNextBattle() {
        if (isGameWon() || !player.isAlive()) return;

        Enemy currentEnemy = this.enemyRepository.getEnemyForStage(this.currentStage);

        if (currentEnemy != null) {
            this.currentBattle = new BattleController(player, currentEnemy);
        }
    }

    public void handleBattleEnd() {
        if (currentBattle == null) return;

        if (currentBattle.isPlayerVictorious()) {
            this.currentStage++;

            if (isGameWon()) {
                System.out.println("\n Hai vinto la partita! ");
            } else {
                System.out.println("\n Battaglia superata, prossima sfida! ");
            }
        } else {
            System.out.println("\n Hai perso la partita! ") ;
        }
    }

    public boolean isGameWon() {
        return this.currentStage >= this.enemyRepository.getTotalStages();
    }
}