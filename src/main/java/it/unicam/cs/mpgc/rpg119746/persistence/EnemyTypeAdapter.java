package it.unicam.cs.mpgc.rpg119746.persistence;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119746.model.enemies.Golem;
import it.unicam.cs.mpgc.rpg119746.model.enemies.Witch;
import it.unicam.cs.mpgc.rpg119746.model.enemies.Yeti;

import java.lang.reflect.Type;

public class EnemyTypeAdapter implements JsonSerializer<Enemy>, JsonDeserializer<Enemy> {

    @Override
    public JsonElement serialize(final Enemy enemy, final Type type, final JsonSerializationContext context) {

        final JsonObject jsonObject = context.serialize(enemy, enemy.getClass()).getAsJsonObject();

        if (enemy instanceof Golem) {
            jsonObject.addProperty("type", "GOLEM");
        } else if (enemy instanceof Witch) {
            jsonObject.addProperty("type", "WITCH");
        } else if (enemy instanceof Yeti) {
            jsonObject.addProperty("type", "YETI");
        }

        return jsonObject;
    }

    @Override
    public Enemy deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final String enemyType = jsonObject.get("type").getAsString();

        if ("GOLEM".equals(enemyType)) {
            return context.deserialize(jsonObject, Golem.class);
        }
        if ("WITCH".equals(enemyType)) {
            return context.deserialize(jsonObject, Witch.class);
        }
        if ("YETI".equals(enemyType)) {
            return context.deserialize(jsonObject, Yeti.class);
        }

        throw new JsonParseException("Unknown enemy type: " + enemyType);
    }
}