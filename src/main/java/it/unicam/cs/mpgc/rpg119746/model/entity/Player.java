package it.unicam.cs.mpgc.rpg119746.model.entity;

public class Player extends GameCharacter{

    private int level;
    private int currentExp;
    private int expToNextLevel;
    private boolean isAbilityActive = false;

    
    public Player (String name, int maxHealthPoints, int baseDamage){

        super(name, maxHealthPoints, baseDamage);
        this.level = 1;
        this.currentExp = 0;
        this.expToNextLevel = 100;
    }

    public int getLevel() { return this.level; }
    public int getCurrentExp() { return this.currentExp; }
    public int getExpToNextLevel() { return this.expToNextLevel; }

@Override
public void attack(GameCharacter target) {
    
    if (this.isAbilityActive) {
        int originalDamage = this.getBaseDamage();
        
        this.setBaseDamage(originalDamage * 2); 
        super.attack(target);                   
        this.setBaseDamage(originalDamage);     
        
        this.isAbilityActive = false;          
    } else {
        
        super.attack(target);
    }
}


@Override
    public void useAbility(GameCharacter target) {
       
        if (!this.isAlive()) {
            throw new IllegalStateException("Il Warden è morto e non può usare abilità!");
        }
        this.isAbilityActive = true; 
    }

public void gainExperience(int exp) {

        if (exp > 0) {
            this.currentExp += exp;

            while (this.currentExp >= this.expToNextLevel) {
                levelUp();
            }
        }
    }

private void levelUp() {

        this.level++;
        this.currentExp -= this.expToNextLevel; 
        this.expToNextLevel += 50;
        this.maxHealthPoints += 50;
        
        this.heal(this.getMaxHealthPoints()); 
}

    @Override
    public String toString() {
       
        return super.toString() + "\n" +
               " Livello: " + this.getLevel() + "\n" + 
               " EXP: " + this.getCurrentExp() + "/" + this.getExpToNextLevel();
    }






}