package ec.edu.uce.JuegoMain.game_package;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.JuegoMain.model_package.HeroData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ServerConnection {

    private HttpClient client;
    private ObjectMapper objectMapper;
    private String url = "http://localhost:8080/miurl";

    public ServerConnection() {
        client = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void sendHeroData(HeroData heroData) {
        try {
            String jsonString = objectMapper.writeValueAsString(heroData);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/miurl"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumeUrl() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Response: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
