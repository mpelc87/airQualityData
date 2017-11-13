import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marek on 13.11.17.
 */
public abstract class HttpRequest {

    //curl -X GET --header 'Accept: application/json' --header 'apikey: 063ca4c899ab43b38af930d0c388834f' 'https://airapi.airly.eu/v1/sensors/current?southwestLat=40&southwestLong=25&northeastLat=55&northeastLong=10'

    public static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_VALUE = "Mozilla/5.0";
    private static final String URL = "https://airapi.airly.eu/v1/sensors/current";
    private static final String SOUTH_WEST_LAT_PARAM = "southwestLat=";
    private static final String SOUTH_WEST_LONG_PARAM = "southwestLong=";
    private static final String NORTH_EAST_LAT_PARAM = "northeastLat=";
    private static final String NORTH_EAST_LONG_PARAM = "northeastLong=";

    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_HEADER_VALUE = "application/json";
    private static final String API_KEY_HEADER = "apikey";
    private static final String API_KEY = "063ca4c899ab43b38af930d0c388834f";

    private static final String REQ_METHOD = "GET";

    private static final int RESPONSE_CODE_200_OK = 200;
    private static final int RESPONSE_CODE_400_BAD_REQUEST = 400;
    private static final int RESPONSE_CODE_401_UNAUTHORIZED = 401;
    private static final int RESPONSE_CODE_403_FORBIDDEN = 403;
    private static final int RESPONSE_CODE_404_NOT_FOUND = 404;
    private static final int RESPONSE_CODE_500_SERVER_ERROR = 500;

    private double southWestLatitude = 40;
    private double southWestLongitude = 25;
    private double northEastLatitude = 55;
    private double northEastLongitude = 10;

    public HttpRequest(double southWestLatitude, double southWestLongitude, double northEastLatitude, double northEastLongitude) {
        this.southWestLatitude = southWestLatitude;
        this.southWestLongitude = southWestLongitude;
        this.northEastLatitude = northEastLatitude;
        this.northEastLongitude = northEastLongitude;
    }

    public String performRequest() {
        HttpURLConnection connection = null;
        StringBuilder responseData = new StringBuilder();
        try {
            connection = createConnection();
            int responseCode = connection.getResponseCode();

            if(responseCode == RESPONSE_CODE_200_OK) {
                BufferedReader responseReader = null;
                try {
                    responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;

                    while ((inputLine = responseReader.readLine()) != null) {
                        responseData.append(inputLine);
                    }

                } finally {
                    responseReader.close();
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

    abstract HttpURLConnection createConnection() throws IOException;

    private String getRequestUrl() {
        return URL + "?" + SOUTH_WEST_LAT_PARAM + southWestLatitude +
                "&" + SOUTH_WEST_LONG_PARAM + southWestLongitude +
                "&" + NORTH_EAST_LAT_PARAM + northEastLatitude +
                "&" + NORTH_EAST_LONG_PARAM + northEastLongitude;
    }
}
