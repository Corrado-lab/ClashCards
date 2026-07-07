package it.unicam.cs.mpgc.rpg119746.persistence;

import it.unicam.cs.mpgc.rpg119746.controller.GameController;
import it.unicam.cs.mpgc.rpg119746.controller.BattleController;
import it.unicam.cs.mpgc.rpg119746.model.entity.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameRepository {
    private final Gson gson;

    public GameRepository() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private static class GameSave {
        Player player;
        int currentStage;
        boolean isBattleActive;
        int enemyHp;
        boolean isPlayerTurn;
    }

    public void saveGame(final GameController gameController, final String filePath) {

        GameSave save = new GameSave();
        save.player = gameController.getPlayer();
        save.currentStage = gameController.getCurrentStage();

        BattleController battle = gameController.getCurrentBattle();
        if (battle != null && !battle.isBattleOver()) {
            save.isBattleActive = true;
            save.enemyHp = battle.getEnemy().getHealthPoints();
            save.isPlayerTurn = battle.isPlayerTurn();
        } else {
            save.isBattleActive = false;
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            this.gson.toJson(save, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Impossibile salvare la partita", e);
        }
    }

    public GameController loadGame(final String filePath) {
        try (FileReader reader = new FileReader(filePath)) {

            GameSave save = this.gson.fromJson(reader, GameSave.class);

            GameController controller = new GameController(save.player, save.currentStage);

            if (save.isBattleActive) {
                controller.startNextBattle();
                BattleController battle = controller.getCurrentBattle();

                if (battle != null) {

                    int damageToApply = battle.getEnemy().getMaxHealthPoints() - save.enemyHp;
                    battle.getEnemy().takeDamage(damageToApply);

                    battle.setPlayerTurn(save.isPlayerTurn);
                }
            }

            return controller;
        } catch (IOException e) {
            throw new IllegalStateException("Impossibile caricare la partita", e);
        }
    }

    public void deleteSaveGame(final String filePath) {
        java.io.File file = new java.io.File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
