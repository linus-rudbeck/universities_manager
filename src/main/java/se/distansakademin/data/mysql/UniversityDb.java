package se.distansakademin.data.mysql;

import se.distansakademin.models.University;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityDb {
    public static List<University> findAll() throws SQLException {
        // Connect & prepare query
        String query = "SELECT * FROM universities;";
        ResultSet rs = MysqlConnector.executeSelect(query);

        // Create empty list for universities
        List<University> universities = new ArrayList<>();

        // Get university data one row at a time
        while (rs.next()){
            University university = new University();

            university.setUniversityId(rs.getInt("university_id"));
            university.setUniversityName(rs.getString("university_name"));
            university.setAddress(rs.getString("address"));
            university.setPhoneNumber(rs.getString("phone_number"));
            university.setEmail("email");

            // Add university to list
            universities.add(university);
        }

        return universities;
    }
}
