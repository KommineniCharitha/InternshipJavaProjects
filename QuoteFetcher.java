package pkg1;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuoteFetcher {

    public static void main(String[] args) {
        try {
            // 1. Define the API endpoint
        	String apiUrl = "https://api.github.com";  // GitHub public API (always online)


            // 2. Create a connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 3. Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // 4. Parse JSON using org.json
            JSONObject json = new JSONObject(response.toString());

            String currentUserUrl = json.getString("current_user_url");
            String emojisUrl = json.getString("emojis_url");

            System.out.println("Current User URL: " + currentUserUrl);
            System.out.println("Emojis URL: " + emojisUrl);


            // 5. Display the output
            System.out.println("\n=== GitHub API Response ===");
            System.out.println("Current User URL: " + currentUserUrl);
            System.out.println("Emojis URL: " + emojisUrl);

         } catch (Exception e) {
            e.printStackTrace();  // Shows full error details in console
        }

    }
}
