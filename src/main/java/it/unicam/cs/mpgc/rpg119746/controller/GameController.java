package it.unicam.cs.mpgc.rpg119746.controller;

import it.unicam.cs.mpgc.rpg119746.model.entity.*;

public class GameController {

    private final Player player;
    private BattleController currentBattle;
    private int currentStage;

    public GameController(String playerName) {
        
        this.player = new Player(Warden, 150, 20);
        this.currentStage = 0;
    }



}