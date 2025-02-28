package se.distansakademin.data.mysql;

import java.sql.*;

public class MysqlConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/schooly_doo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {

        // Kolla om en connection finns
        if(connection == null){

            // Öppna & spara connection om den inte finns
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        // Returnera connection
        return connection;
    }

    public static ResultSet executeSelect(String selectQuery) throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(selectQuery);
        return rs;
    }

}
