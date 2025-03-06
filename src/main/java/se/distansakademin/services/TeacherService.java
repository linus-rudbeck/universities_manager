package se.distansakademin.services;

import se.distansakademin.data.mysql.TeacherDb;
import se.distansakademin.models.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherService {

    public List<Teacher> getTeachers() throws SQLException {
        List<Teacher> teachers = TeacherDb.findAll();

        return teachers;
    }

    // "NAMN1, NAMN2"
    public String getTeacherNames() throws SQLException {
        List<Teacher> teachers = TeacherDb.findAll();

        return getNamesString(teachers);
    }

    private static String getNamesString(List<Teacher> teachers) {
        StringBuilder names = new StringBuilder();

        for (Teacher teacher : teachers){
            names.append(teacher.getTeacherName()).append(", ");
        }

        return names.substring(0, names.length() - 2);
    }

}
