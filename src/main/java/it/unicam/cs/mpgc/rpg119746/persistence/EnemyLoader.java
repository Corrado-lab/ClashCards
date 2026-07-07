package it.unicam.cs.mpgc.rpg119746.persistence;

import it.unicam.cs.mpgc.rpg119746.model.characters.Enemy;
import java.util.List;

public interface EnemyLoader {
    
    List<Enemy> loadEnemies();
}