package se.distansakademin;

import se.distansakademin.data.mysql.MysqlConnector;
import se.distansakademin.data.mysql.UniversityDb;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // Show universities
        try {
            var universities = UniversityDb.findAll();
            universities.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Show first university
        try{
            var universities = UniversityDb.findAll();
            System.out.println(universities.getFirst());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showUniversities() throws SQLException {
        String query = "SELECT * FROM universities;";
        Connection connection = MysqlConnector.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()){
            int universityId = rs.getInt("university_id");
            String universityName = rs.getString("university_name");

            System.out.println(universityId + ": " + universityName);
        }
    }
}