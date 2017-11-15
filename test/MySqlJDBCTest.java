import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 13.11.17.
 */
public class MySqlJDBCTest {
    @Test
    public void createDatabaseTest() {
        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        mySqlJDBC.createDatabaseIfNotExist();
    }

    @Test
    public void createSchemaTest() {
        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        mySqlJDBC.createSchemaIfNotExist();
    }

    @Test
    public void insertSensorsTest() {
        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        mySqlJDBC.createDatabaseIfNotExist();
        mySqlJDBC.createSchemaIfNotExist();

        List<Sensor> sensors = new ArrayList<>();
        Sensor sensor = new Sensor.SensorBuilder("1", "name1" , "51.1234", "25.12312")
                .country("Poland")
                .locality("Malopółską")
                .route("Słomczyńskiego")
                .street_number("12")
                .vendor("Vendor1")
                .build();
        sensors.add(sensor);

        sensor = new Sensor.SensorBuilder("2", "name2" , "52.1234", "22.12312")
                .country("Poland")
                .locality("Malopolska")
                .route("Rynek")
                .street_number("1")
                .vendor("Vendor1")
                .build();

        sensors.add(sensor);

        mySqlJDBC.insert(sensors);
    }
}
