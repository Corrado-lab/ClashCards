package it.unicam.cs.mpgc.rpg119746.persistence;

import it.unicam.cs.mpgc.rpg119746.model.entity.Enemy;
import it.unicam.cs.mpgc.rpg119746.model.entity.Golem;
import it.unicam.cs.mpgc.rpg119746.model.entity.Witch;
import it.unicam.cs.mpgc.rpg119746.model.entity.Yeti;
import it.unicam.cs.mpgc.rpg119746.model.entity.GameCharacter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EnemyRepository implements EnemyLoader {

    @Override
    public List<Enemy> loadEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("enemies.xml");
            if (inputStream == null) {
                throw new IllegalStateException("File enemies.xml non trovato nelle risorse");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xpath.compile("/enemies/enemy");
            NodeList enemyNodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < enemyNodes.getLength(); i++) {
                Element enemyElement = (Element) enemyNodes.item(i);

                String name = xpath.compile("name").evaluate(enemyElement);
                int maxHealth = Integer.parseInt(xpath.compile("maxHealth").evaluate(enemyElement));
                int attack = Integer.parseInt(xpath.compile("attack").evaluate(enemyElement));
                int rewardExperience = Integer.parseInt(xpath.compile("rewardExperience").evaluate(enemyElement));

                switch (name.toLowerCase()) {
                    case "golem":
                        enemies.add(new Golem(name, maxHealth, attack, rewardExperience));
                        break;

                    case "witch":
                        enemies.add(new Witch(name, maxHealth, attack, rewardExperience));
                        break;

                    case "yeti":
                        enemies.add(new Yeti(name, maxHealth, attack, rewardExperience));
                        break;

                    default:

                        throw new IllegalArgumentException("Tipo di nemico sconosciuto nel file XML: " + name);
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException("Errore durante il parsing XPath del file XML dei mostri", e);
        }

        return enemies;
    }

    public Enemy getEnemyForStage(int stage) {
        List<Enemy> allEnemies = this.loadEnemies();
        if (stage >= 0 && stage < allEnemies.size()) {
            return allEnemies.get(stage);
        }
        return null;
    }

    public int getTotalStages() {
        return this.loadEnemies().size();
    }
}