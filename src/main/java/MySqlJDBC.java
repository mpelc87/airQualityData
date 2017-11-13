import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<String []> argList = new ArrayList<String[]>();
        for(Sensor sensor : sensors) {
            argList.add(new String[]{sensor.getId(), sensor.getName(), sensor.getVendor(), sensor.getLatitude(), sensor.getLongitude(),
            sensor.getStreetNumber(), sensor.getRoute(), sensor.getLocality(), sensor.getCountry()});
        }
        executeQueriesWithParams(INSERT_SENSOR_QUERY, argList);
    }

    public void executeQueriesWithParams(String sqlQuery, List<String []> argsList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL_WITH_DB_NAME, USER, PASSWORD);
            statement = connection.prepareStatement(sqlQuery);

            for(String [] args : argsList) {
                for(int i=0; i<args.length; ++i) {
                    statement.setString(i+1, args[i]);
                }
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createSchema() {
        executeQuery(CREATE_TABLE_SENSORS_QUERY);
    }

    private void executeQuery(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL_WITH_DB_NAME, USER, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createDatabase() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = CREATE_DATABASE_QUERY;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
