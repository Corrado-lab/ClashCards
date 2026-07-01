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


}
