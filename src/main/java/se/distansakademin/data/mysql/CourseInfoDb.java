package se.distansakademin.data.mysql;

import se.distansakademin.models.CourseInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseInfoDb {

    public static List<CourseInfo> findAll() throws SQLException {

        String query = """
                SELECT course_id, course_name, university_name, teacher_name FROM courses
                JOIN universities ON courses.university_id = universities.university_id
                JOIN teachers ON courses.teacher_id = teachers.teacher_id;
                """;
        ResultSet rs = MysqlConnector.executeSelect(query);

        List<CourseInfo> courseInfoList = new ArrayList<>();

        while (rs.next()){
            CourseInfo courseInfo = new CourseInfo();

            courseInfo.setCourseId(rs.getInt("course_id"));
            courseInfo.setCourseName(rs.getString("course_name"));
            courseInfo.setUniversityName(rs.getString("university_name"));
            courseInfo.setTeacherName(rs.getString("teacher_name"));

            courseInfoList.add(courseInfo);
        }

        return courseInfoList;
    }
}
