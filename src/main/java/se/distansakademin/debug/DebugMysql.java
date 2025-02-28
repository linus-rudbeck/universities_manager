package se.distansakademin.debug;

import se.distansakademin.data.mysql.CourseInfoDb;
import se.distansakademin.data.mysql.TeacherDb;
import se.distansakademin.data.mysql.UniversityDb;

import java.sql.SQLException;

public class DebugMysql {

    public static void main(String[] args) throws SQLException {

        // Show all universities
        var universities = UniversityDb.findAll();
        universities.forEach(System.out::println);

        // Show all teachers
        var teacher = TeacherDb.findAll();
        teacher.forEach(System.out::println);

        // Show all course info
        var courseInfoList = CourseInfoDb.findAll();
        courseInfoList.forEach(System.out::println);

    }
}
