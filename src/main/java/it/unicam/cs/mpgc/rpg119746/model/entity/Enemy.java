package it.unicam.cs.mpgc.rpg119746.model.entity;

public class Enemy extends GameCharacter{

    private final int expReward;

    public Enemy (String name, int maxHealthPoints, int baseDamage, int expReward){

        super(name, maxHealthPoints, baseDamage);
        this.expReward=expReward;

    }

    public int getExpReward() {return this.expReward; }

}
