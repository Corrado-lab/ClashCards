package it.unicam.cs.mpgc.rpg119746.model;

import it.unicam.cs.mpgc.rpg119746.controller.GameController;
import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {

    @Test
    void testPlayerAttackReducesEnemyHp() {

        GameController gameController = new GameController("Warden");
        gameController.startNextBattle();
        var battle = gameController.getCurrentBattle();

        Enemy enemy = battle.getEnemy();
        int hpIniziali = enemy.getHealthPoints();

        battle.executePlayerAttack();

        assertTrue(enemy.getHealthPoints() < hpIniziali);
    }

    @Test
    void verifyInitialBattleState() {
        GameController gameController = new GameController("Warden");
        gameController.startNextBattle();
        var battle = gameController.getCurrentBattle();

        assertFalse(battle.isBattleOver());
    }

    @Test
    void testSpecialAbilityExecution() {
        GameController gameController = new GameController("Warden");
        gameController.startNextBattle();
        var battle = gameController.getCurrentBattle();

        assertDoesNotThrow(() -> battle.executePlayerAbility());
    }
}
