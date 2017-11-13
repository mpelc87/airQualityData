import org.junit.Test;

/**
 * Created by marek on 13.11.17.
 */
public class MySqlJDBCTest {
    @Test
    public void createDatabaseTest() {
        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        mySqlJDBC.createDatabase();
    }

    @Test
    public void createSchemaTest() {
        MySqlJDBC mySqlJDBC = new MySqlJDBC();
        mySqlJDBC.createSchema();
    }
}
