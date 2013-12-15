package pokedex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RemoteDB {
    private static RemoteDB INSTANCE = null;
    private static String URL = "jdbc:h2:tcp://146.185.168.126:9092/pokedex;IFEXISTS=TRUE";
    private static Connection connection = null;

    public static RemoteDB getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDB();
        }
        return INSTANCE;
    }

    private RemoteDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(URL, "sa", "");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        RemoteDB.getInstance();
    }
}
