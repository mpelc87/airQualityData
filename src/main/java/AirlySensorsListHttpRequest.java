import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marek on 13.11.17.
 */
public class AirlySensorsListHttpRequest extends HttpRequest {

    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_VALUE = "Mozilla/5.0";
    private static final String URL = "https://airapi.airly.eu/v1/sensors/current";
    private static final String SOUTH_WEST_LAT_PARAM = "southwestLat=";
    private static final String SOUTH_WEST_LONG_PARAM = "southwestLong=";
    private static final String NORTH_EAST_LAT_PARAM = "northeastLat=";
    private static final String NORTH_EAST_LONG_PARAM = "northeastLong=";

    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_HEADER_VALUE = "application/json";
    private static final String API_KEY_HEADER = "apikey";

    private static final String REQ_METHOD = "GET";

    private double southWestLatitude;
    private double southWestLongitude;
    private double northEastLatitude;
    private double northEastLongitude;

    public AirlySensorsListHttpRequest(double southWestLatitude, double southWestLongitude, double northEastLatitude, double northEastLongitude) {
        this.southWestLatitude = southWestLatitude;
        this.southWestLongitude = southWestLongitude;
        this.northEastLatitude = northEastLatitude;
        this.northEastLongitude = northEastLongitude;
    }

    @Override
    HttpURLConnection createConnection(String apiKey) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(getRequestUrl()).openConnection();
        connection.setRequestMethod(REQ_METHOD);
        connection.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
        connection.setRequestProperty(ACCEPT_HEADER, ACCEPT_HEADER_VALUE);
        connection.setRequestProperty(API_KEY_HEADER, apiKey);

        return connection;
    }

    private String getRequestUrl() {
        return URL + "?" + SOUTH_WEST_LAT_PARAM + southWestLatitude +
                "&" + SOUTH_WEST_LONG_PARAM + southWestLongitude +
                "&" + NORTH_EAST_LAT_PARAM + northEastLatitude +
                "&" + NORTH_EAST_LONG_PARAM + northEastLongitude;
    }
}
