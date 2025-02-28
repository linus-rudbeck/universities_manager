package se.distansakademin.models;

public class CourseInfo {

    int courseId;
    String courseName;
    String universityName;
    String teacherName;

    public CourseInfo() { }

    public CourseInfo(String courseName, String universityName, String teacherName) {
        this.courseName = courseName;
        this.universityName = universityName;
        this.teacherName = teacherName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", universityName='" + universityName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
