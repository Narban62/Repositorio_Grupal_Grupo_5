package controller_package;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerConnection {
    private HttpClient client;
    private ObjectMapper objectMapper;

    public ServerConnection() {
        client = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void sendHeroData(HeroData heroData) {
        try {
            String jsonString = objectMapper.writeValueAsString(heroData);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://your-server-url/endpoint"))
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
}
