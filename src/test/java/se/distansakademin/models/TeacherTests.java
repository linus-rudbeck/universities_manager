package se.distansakademin.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTests {

    @Test
    public void testConstructorAndGetters(){
        // Arrange
        var teacher = new Teacher("TEST_TEACHER", "TEST_PHONE", "TEST_EMAIL");

        // Act
        var teacherName = teacher.getTeacherName();
        var teacherPhone = teacher.getPhoneNumber();
        var teacherEmail = teacher.getEmail();

        //Assert
        assertEquals("TEST_TEACHER", teacherName);
        assertEquals("TEST_PHONE", teacherPhone);
        assertEquals("TEST_EMAIL", teacherEmail);
    }
}
