import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by marek on 13.11.17.
 */
public class MySqlJDBC {
    static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static private final String DB_NAME = "AIR_QUALITY_DATA";

    static private final String DB_URL = "jdbc:mysql://localhost/";
    static private final String DB_URL_WITH_DB_NAME = "jdbc:mysql://localhost/" + DB_NAME;
    static private final String USER = "root";
    static private final String PASSWORD = "root";
    public static final String CREATE_DATABASE_QUERY = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
    public static final String CREATE_TABLE_SENSORS = "CREATE TABLE IF NOT EXISTS SENSORS " +
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

    public void createSchema() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL_WITH_DB_NAME, USER, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_SENSORS);

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
