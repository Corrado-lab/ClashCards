package it.unicam.cs.mpgc.rpg119746.model.characters;

public abstract class Enemy extends GameCharacter {

    private final int expReward;

    public Enemy (String name, int maxHealthPoints, int baseDamage, int expReward){

        super(name, maxHealthPoints, baseDamage);
        this.expReward=expReward;

    }

    public abstract void takeTurn(GameCharacter player);

    public int getExpReward() {return this.expReward; }

    @Override
    public String toString() {
       
        return super.toString() + "\n" +
               " Ricompensa EXP: " + this.getExpReward();
    }


}
