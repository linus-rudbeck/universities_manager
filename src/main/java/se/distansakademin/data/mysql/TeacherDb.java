package se.distansakademin.data.mysql;

import se.distansakademin.models.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDb {

    public static List<Teacher> findAll() throws SQLException{
        // Connect & prepare query
        String query = "SELECT * FROM teachers";
        ResultSet rs = MysqlConnector.executeSelect(query);

        // Create empty list of teachers
        List<Teacher> teachers = new ArrayList<>();

        // Get teacher data one row at a time
        while(rs.next()){
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacher_id"));
            teacher.setTeacherName(rs.getString("teacher_name"));
            teacher.setPhoneNumber(rs.getString("phone_number"));
            teacher.setEmail(rs.getString("email"));

            // Add teacher to list
            teachers.add(teacher);
        }

        // Return teacher list
        return teachers;
    }
}
