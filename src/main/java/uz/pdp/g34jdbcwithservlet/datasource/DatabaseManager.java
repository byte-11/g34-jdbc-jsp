package uz.pdp.g34jdbcwithservlet.datasource;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234psql";

    public static Connection connect(){
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
