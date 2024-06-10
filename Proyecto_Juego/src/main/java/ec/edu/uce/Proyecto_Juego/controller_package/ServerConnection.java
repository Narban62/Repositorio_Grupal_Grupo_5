package ec.edu.uce.Proyecto_Juego.controller_package;

import java.util.HashMap;
import java.util.Map;

public class ServerConnection {

    // Simulación de almacenamiento de datos en memoria
    private static final Map<String, GameData> savedGames = new HashMap<>();

    public static void saveGame(String playerName, int score, int life) {
        GameData gameData = new GameData(playerName, score, life);
        savedGames.put(playerName, gameData);
    }

    public static GameData loadGame(String playerName) {
        return savedGames.get(playerName);
    }

    // Clase interna para los datos del juego
    public static class GameData {
        private final String playerName;
        private final int score;
        private final int life;

        public GameData(String playerName, int score, int life) {
            this.playerName = playerName;
            this.score = score;
            this.life = life;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public int getLife() {
            return life;
        }
    }
}
