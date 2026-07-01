package it.unicam.cs.mpgc.rpg119746.model.entity;

public abstract class GameCharacter {

    private final String name;
    private int healthPoints;
    private final int maxHealthPoints;
    private final int baseDamage;
    private boolean isAlive;

    public GameCharacter(String name, int maxHealthPoints, int baseDamage){

        this.name= name;
        this.healthPoints= maxHealthPoints;
        this.maxHealthPoints= maxHealthPoints;
        this.baseDamage= baseDamage;
        this.isAlive=true;


    }

    public String getName() { return name; }
    public int getHealthPoints() { return healthPoints; }
    public int getMaxHealthPoints() { return maxHealthPoints; }
    public int getBaseDamage() { return baseDamage; }

    public boolean isAlive() { return this.isAlive; }

    public void attack(GameCharacter target) {

        if (target == null) {
            throw new NullPointerException("Il bersaglio dell'attacco non può essere null.");
        }
        if (!this.isAlive()) {
            throw new IllegalStateException(this.name + " è morto e non può attaccare!");
        }
        if (!target.isAlive()) {
            throw new IllegalStateException("Non puoi attaccare " + target.getName() + " perché è già morto!");
        }

        target.takeDamage(this.baseDamage);
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo! Danno inserito: " + damage);
        }
        
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
            this.isAlive = false;
        }
    }
    
    public abstract void useAbility(GameCharacter target);


}
