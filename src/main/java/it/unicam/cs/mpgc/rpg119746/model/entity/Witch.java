package it.unicam.cs.mpgc.rpg119746.model.entity;

public class Witch extends Enemy{

    private boolean hasUsedAbility = false;

    public Witch (){

        super("Witch", 100, 20, 80);
    }

    @Override
    public void useAbility(GameCharacter target) {
        if (!this.isAlive()) {
            throw new IllegalStateException("La Strega è stata distrutta!");
        }
        
        this.heal(40);
        
        int newDamage = Math.max(0, this.getBaseDamage() - 10);
        this.setBaseDamage(newDamage);
        
        this.hasUsedAbility = true;
    }

    @Override
    public void takeTurn(GameCharacter player) {
        
        
        if (this.getHealthPoints() <= 40 && !this.hasUsedAbility) {
            
            this.useAbility(player);
            this.attack(player);     
            
        } else {

            this.attack(player);
        }
    }
}


