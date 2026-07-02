package it.unicam.cs.mpgc.rpg119746.model.entity;

public class Yeti extends Enemy{

    public Yeti (){

        super("Yeti", 150, 30, 70);

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

