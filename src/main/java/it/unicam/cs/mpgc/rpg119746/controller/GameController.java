package it.unicam.cs.mpgc.rpg119746.controller;

import it.unicam.cs.mpgc.rpg119746.model.entity.*;

public class GameController {

    private final Player player;
    private BattleController currentBattle;
    private int currentStage;

    public GameController(String playerName) {
        
        this.player = new Player(playerName, 150, 20);
        this.currentStage = 0;
    }

    public Player getPlayer() { return player; }
    public BattleController getCurrentBattle() { return currentBattle; }
    public int getCurrentStage() { return currentStage; }

    private Enemy createEnemyForCurrentStage() {
        switch (this.currentStage) {
            case 0: return new Golem();
            case 1: return new Witch();
            case 2: return new Yeti();
            default: return null;
        }
    }

    public void startNextBattle() {
        if (isGameWon() || !player.isAlive()) return;

        Enemy currentEnemy = createEnemyForCurrentStage();
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
        return this.currentStage > 2;
    }

}