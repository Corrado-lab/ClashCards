package it.unicam.cs.mpgc.rpg119746.model.entity;

public abstract class Enemy extends GameCharacter{

    private final int expReward;

    public Enemy (String name, int maxHealthPoints, int baseDamage, int expReward){

        super(name, maxHealthPoints, baseDamage);
        this.expReward=expReward;

    }

    public abstract void takeTurn(GameCharacter player);

    public int getExpReward() {return this.expReward; }

}
