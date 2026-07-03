package it.unicam.cs.mpgc.rpg119746.controller;

import it.unicam.cs.mpgc.rpg119746.model.entity.Player;
import it.unicam.cs.mpgc.rpg119746.model.entity.Enemy;

public class BattleController {

    private final Player player;
    private final Enemy enemy;
    private boolean isPlayerTurn;


    public BattleController(Player player, Enemy enemy) {
        if (player == null || enemy == null) {
            throw new IllegalArgumentException("Player ed Enemy non possono essere null.");
        }
        this.player = player;
        this.enemy = enemy;
        this.isPlayerTurn = true; 
    }


    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public boolean isPlayerTurn() { return isPlayerTurn; }


    public void executePlayerAttack() {
        if (!isPlayerTurn || !player.isAlive() || !enemy.isAlive()) return;

        player.attack(enemy);
        endPlayerTurn();
    }

    
    public void executePlayerAbility() {
        if (!isPlayerTurn || !player.isAlive() || !enemy.isAlive()) return;

        player.useAbility(enemy);
        System.out.println("Il Warden infliggerà danni doppi!");
        
    }

    
    private void endPlayerTurn() {
        if (!enemy.isAlive()) {
            handleVictory();
        } else {

            this.isPlayerTurn = false;
            executeEnemyTurn();
        }
    }

    
    private void executeEnemyTurn() {
        
        enemy.takeTurn(player);
        
        if (!player.isAlive()) {
            handleDefeat();
        } else {

            this.isPlayerTurn = true;
        }
    }

    
    private void handleVictory() {
        System.out.println("\n Bene! Hai sconfitto " + enemy.getName() + "!");
        player.gainExperience(enemy.getExpReward());
    }

    
    private void handleDefeat() {
        System.out.println("\n Il Warden è è stato sconfitto, hai perso.");
    }
    
}
