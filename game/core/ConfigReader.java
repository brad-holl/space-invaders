package game.core;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */
	public GameStats parse(String path) {

		JSONParser parser = new JSONParser();
		try {
			URL resource = getClass().getClassLoader().getResource(path);
			if(resource == null) return null;
			String file = resource.getFile();
			Object object = parser.parse(new FileReader(file));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the player section:
			JSONObject jsonPlayer = (JSONObject) jsonObject.get("player");
			GameStats gameStats = new GameStats((Long)jsonPlayer.get("lives"),
					(Long)jsonPlayer.get("startPositionX"),
					(Long) jsonPlayer.get("startPositionY"));
			gameStats.setPlayerColor((String) jsonPlayer.get("color"));
			gameStats.setSpeed(4);
			JSONObject gameSize = (JSONObject) jsonObject.get("game") ;
			gameStats.setWidth((Long) gameSize.get("width"));
			gameStats.setHeight((Long) gameSize.get("height"));
			// reading the bunker section:
			JSONArray bunker = (JSONArray) jsonObject.get("bunkers");
			for (Object obj : bunker) {
				JSONObject jsonBunker = (JSONObject) obj;
				BunkerStats bunkerStats = new BunkerStats(
				(Long) jsonBunker.get("startX"),
				(Long) jsonBunker.get("startY"), (Long) jsonBunker.get("width"), (Long) jsonBunker.get("height"));
				gameStats.getBunkerStats().add(bunkerStats);
			}
			// reading the projectile section:
			JSONObject projectile = (JSONObject) jsonObject.get("projectile");
			gameStats.setProjectileDamage(1000);
			gameStats.setProjectileSpeed(3);

			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				Long x = (Long) jsonEnemy.get("x");
				Long y = (Long) jsonEnemy.get("y");
				String strat = (String) jsonEnemy.get("projectileStrategy");
				EnemyStats enemyStats = new EnemyStats(x,y,strat);
				// the enemy position is a double

				gameStats.getEnemyStats().add(enemyStats);
			}
			System.out.println(gameStats);
			return gameStats;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
