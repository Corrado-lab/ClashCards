package it.unicam.cs.mpgc.rpg119746.model;

import it.unicam.cs.mpgc.rpg119746.model.characters.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void playerTakesDamage() {

        Player player = new Player("Warden", 100, 15);

        player.takeDamage(30);

        assertEquals(70, player.getHealthPoints());
        assertTrue(player.isAlive());
    }

    @Test
    void healingCannotExceedMaxHp() {
        Player player = new Player("Warden", 100, 15);

        player.takeDamage(10);
        assertEquals(90, player.getHealthPoints());

        player.heal(50);

        assertEquals(100, player.getHealthPoints());
    }

    @Test
    void zeroHpTriggersDefeat() {
        Player player = new Player("Warden", 100, 15);

        player.takeDamage(120);

        assertEquals(0, player.getHealthPoints());
        assertFalse(player.isAlive());
    }

    @Test
    void gainingExpTriggersLevelUp() {
        Player player = new Player("Warden", 100, 15);

        player.gainExperience(120);

        assertEquals(2, player.getLevel());
        assertEquals(20, player.getCurrentExp());
        assertEquals(150, player.getExpToNextLevel());
    }
}
