import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by marek on 13.11.17.
 */
public abstract class HttpRequest {

    private static final int RESPONSE_CODE_200_OK = 200;

    private static final String API_KEY = "063ca4c899ab43b38af930d0c388834f";

    String performRequest() {
        HttpURLConnection connection = null;
        StringBuilder responseData = new StringBuilder();
        try {
            connection = createConnection(API_KEY);
            int responseCode = connection.getResponseCode();

            if(responseCode == RESPONSE_CODE_200_OK) {

                try(BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = responseReader.readLine()) != null) {
                        responseData.append(inputLine);
                    }
                }
            } else {
                throw new IOException("HTTP connection problem - " + responseCode +" : " + connection.getResponseMessage());
            }

        } catch(IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return responseData.toString();
    }

    abstract HttpURLConnection createConnection(String apiKey) throws IOException;
}
