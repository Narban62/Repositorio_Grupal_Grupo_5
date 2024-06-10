package ec.edu.uce.Proyecto_Juego.controller_package;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.Proyecto_Juego.model_package.objects.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ServerConnection {

    private HttpClient client;
    private ObjectMapper objectMapper;
    private static Map<String, User> savedGames = new HashMap<>();

    public ServerConnection() {
        client = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void saveGame(String user, int level, int score, int life) {
        User gameData = new User(user, level, score, life);
        savedGames.put(user, gameData);
        updateHeroData(user, level, score, life);
    }

    public void sendUserData(User user) {
        try {
            String jsonString = objectMapper.writeValueAsString(user);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/register"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
            }else {
                System.out.println("Response: " + response.body());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHeroData(String user,int level, int score, int life) {
        try {
            String jsonString2 = objectMapper.writeValueAsString(new User(user, level, score, life));
            System.out.println(jsonString2);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/updateHeroData"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonString2))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
            }else {
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public User getSavedState(String playerName) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/getUserData?username="+playerName ))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Response: " + response.body());
                return objectMapper.readValue(response.body(), User.class);
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
