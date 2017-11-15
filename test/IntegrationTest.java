import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marek on 15.11.17.
 */
public class IntegrationTest {

    @Test
    public void performHttpAndSaveToDatabase() {
        long testStart = System.currentTimeMillis();
        double southWestLatitude = 40;
        double southWestLongitude = 25;
        double northEastLatitude = 55;
        double northEastLongitude = 10;

        AirlySensorsListHttpRequest airlySensorsListHttpRequest = new AirlySensorsListHttpRequest(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude);
        String result = airlySensorsListHttpRequest.performRequest();

        AirlyJsonParser jsonParser = new AirlyJsonParser();
        List<Map<String, String>> paramsList = jsonParser.parseJsonString(result);

        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        List<Sensor> sensors = new ArrayList<>();

        for(Map<String, String> params : paramsList) {
            sensors.add(new Sensor.SensorBuilder(params.get(AirlyJsonParser.ID),
                    params.get(AirlyJsonParser.NAME),
                    params.get(AirlyJsonParser.LATITUDE),
                    params.get(AirlyJsonParser.LONGITUDE))
                    .vendor(params.get(AirlyJsonParser.VENDOR))
                    .street_number(params.get(AirlyJsonParser.STREET_NUMBER))
                    .route(params.get(AirlyJsonParser.ROUTE))
                    .locality(params.get(AirlyJsonParser.LOCALITY))
                    .country(params.get(AirlyJsonParser.COUNTRY))
                    .build());
        }

        mySqlJDBC.createDatabaseIfNotExist();
        mySqlJDBC.createSchemaIfNotExist();
        mySqlJDBC.insert(sensors);

        System.out.println("Test duration: " + (System.currentTimeMillis() - testStart) + " ms");

    }
}
