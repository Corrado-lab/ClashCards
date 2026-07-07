package it.unicam.cs.mpgc.rpg119746.model.enemies;

import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119746.model.characters.GameCharacter;

public class Yeti extends Enemy {

    public Yeti(String name, int maxHealthPoints, int baseDamage, int expReward) {

        super(name, maxHealthPoints, baseDamage, expReward);
    }

    @Override
    public void useAbility(GameCharacter target) {
        if (!this.isAlive()) {
            throw new IllegalStateException("Lo Yeti è stato distrutto!");
        }
        
        super.attack(target);
        super.attack(target);
    }

    @Override
    public void takeTurn(GameCharacter player) {
        
        
        double probabilita = Math.random();
        
        if (probabilita <= 0.30) {
            
            this.useAbility(player); 
            
        } else {
            
            this.attack(player);     
            
        }
    }
}

