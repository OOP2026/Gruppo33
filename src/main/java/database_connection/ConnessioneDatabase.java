package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    public Connection connection= null;
    private static final String NOME = "postgres";
    private static final String PASSWORD = "Database45";
    private static final String URL= "jdbc:postgresql://localhost:5432/Ospedale";
    private static final String DRIVER = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, NOME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection CreationFailed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}