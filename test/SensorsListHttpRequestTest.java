import org.junit.Test;

import java.io.IOException;

/**
 * Created by marek on 13.11.17.
 */
public class SensorsListHttpRequestTest {

    @Test
    public void performRequestTest() throws IOException {
        double southWestLatitude = 40;
        double southWestLongitude = 25;
        double northEastLatitude = 55;
        double northEastLongitude = 10;

        SensorsListHttpRequest sensorsListHttpRequest = new SensorsListHttpRequest(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude);
        String result = sensorsListHttpRequest.performRequest();
        System.out.println("Result: " + result);
    }

}
