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

    
}
