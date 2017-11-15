import org.junit.Test;

import java.io.IOException;

/**
 * Created by marek on 13.11.17.
 */
public class AirlySensorsListHttpRequestTest {

    @Test
    public void performRequestTest() throws IOException {
        double southWestLatitude = 40;
        double southWestLongitude = 25;
        double northEastLatitude = 55;
        double northEastLongitude = 10;

        AirlySensorsListHttpRequest airlySensorsListHttpRequest = new AirlySensorsListHttpRequest(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude);
        String result = airlySensorsListHttpRequest.performRequest();
        System.out.println("Result: " + result);
    }

}
