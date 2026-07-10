package it.unicam.cs.mpgc.rpg119746.persistence;

import it.unicam.cs.mpgc.rpg119746.controller.GameController;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    @Test
    void testSaveAndLoadConsistency() {

        GameController originalController = new GameController("WARDEN_TEST");
        originalController.startNextBattle();

        GameRepository repository = new GameRepository();
        String percorsoTest = "test-savegame.json";

        repository.saveGame(originalController, percorsoTest);

        GameController loadedController = repository.loadGame(percorsoTest);

        try {
            assertNotNull(loadedController);
            assertEquals("WARDEN_TEST", loadedController.getPlayer().getName());
        } finally {
            File fileDiTest = new File(percorsoTest);
            if (fileDiTest.exists()) {
                fileDiTest.delete();
            }
        }
    }
}