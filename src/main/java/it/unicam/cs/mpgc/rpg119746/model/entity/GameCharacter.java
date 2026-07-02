package it.unicam.cs.mpgc.rpg119746.model.entity;

public abstract class GameCharacter {

    private final String name;
    private int healthPoints;
    private final int maxHealthPoints;
    private  int baseDamage;
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

    public void setBaseDamage(int damage){
        if(damage<0){
        throw new IllegalArgumentException("Il danno non puo essere negativo");
        }
    this.baseDamage=damage;}

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

     public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("La quantità di cura non può essere negativa! Cura inserita: " + amount);
        }

        if (this.isAlive()) {
        
            this.healthPoints = Math.min(this.maxHealthPoints, this.healthPoints + amount);
        }
    }

    @Override
    public String toString() {
        
        return this.getName() + 
               " HP: " + this.getHealthPoints() + "/" + this.getMaxHealthPoints()  + "\n" +
               " Danno Base: " + this.getBaseDamage() + "\n" + 
               " Vivo: " + this.isAlive();
    }



}
