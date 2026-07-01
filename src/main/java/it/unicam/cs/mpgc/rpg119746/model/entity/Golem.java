package it.unicam.cs.mpgc.rpg119746.model.entity;

public class Golem extends Enemy{

    private boolean isAbilityActive = false;
    private int turnCounter = 0;

    public Golem (){

        super("Golem", 200, 10, 50);

    }

    @Override
    public void useAbility(GameCharacter target) {

        if (!this.isAlive()) {
            throw new IllegalStateException("Il Golem è stato distrutto!");
        }
    
        this.isAbilityActive = true; 
        
    }

    @Override
    public void attack(GameCharacter target) {

        if (this.isAbilityActive) {
            int originalDamage = this.getBaseDamage();
            this.setBaseDamage(originalDamage * 3); 
            super.attack(target);
            this.setBaseDamage(originalDamage); 
            this.isAbilityActive = false; 

        } else {
            super.attack(target);
        }
    }

    @Override
    public void takeTurn(GameCharacter player) {
        
        this.turnCounter++;
        
        if (this.turnCounter == 3) {
            this.useAbility(player); 
            this.attack(player);    
            
            this.turnCounter = 0;   
        } else {
            
            this.attack(player);
        }
    }
}




