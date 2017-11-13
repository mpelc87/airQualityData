import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 13.11.17.
 */
public class MySqlJDBC {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_NAME = "AIR_QUALITY_DATA";

    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String DB_URL_WITH_DB_NAME = "jdbc:mysql://localhost/" + DB_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
    private static final String CREATE_TABLE_SENSORS_QUERY = "CREATE TABLE IF NOT EXISTS SENSORS " +
                                                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                                                        " sensor_id VARCHAR(20) not NULL, " +
                                                        " name VARCHAR(30), " +
                                                        " vendor VARCHAR(30), " +
                                                        " latitude VARCHAR(40), " +
                                                        " longitude VARCHAR(40), " +
                                                        " street_number VARCHAR(10), " +
                                                        " route VARCHAR(100), " +
                                                        " locality VARCHAR(100), " +
                                                        " country VARCHAR(100), " +
                                                        " PRIMARY KEY ( id ))";
    private static final String INSERT_SENSOR_QUERY = "INSERT INTO SENSORS (sensor_id, name, vendor, latitude, longitude, " +
            "street_number, route, locality, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insert(List<Sensor> sensors) {
        List<String []> argList = new ArrayList<>();
        for(Sensor sensor : sensors) {
            argList.add(new String[]{sensor.getId(), sensor.getName(), sensor.getVendor(), sensor.getLatitude(), sensor.getLongitude(),
            sensor.getStreetNumber(), sensor.getRoute(), sensor.getLocality(), sensor.getCountry()});
        }
        executeQueriesWithParams(INSERT_SENSOR_QUERY, argList);
    }

    private void executeQueriesWithParams(String sqlQuery, List<String []> argsList) {
        try(Connection connection = DriverManager.getConnection(DB_URL_WITH_DB_NAME, USER, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            Class.forName(JDBC_DRIVER);

            for(String [] args : argsList) {
                for(int i=0; i<args.length; ++i) {
                    statement.setString(i+1, args[i]);
                }
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Class not found exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void createSchema() {
        executeQuery(CREATE_TABLE_SENSORS_QUERY);
    }

    public void createDatabase() {
        executeQuery(CREATE_DATABASE_QUERY, DB_URL);
    }

    private void executeQuery(String query) {
        executeQuery(query, DB_URL_WITH_DB_NAME);
    }

    private void executeQuery(String query, String dbUrl) {
        try(Connection connection = DriverManager.getConnection(dbUrl, USER, PASSWORD); Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER);
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Class not found exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
