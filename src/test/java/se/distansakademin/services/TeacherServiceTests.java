package se.distansakademin.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import se.distansakademin.data.mysql.TeacherDb;
import se.distansakademin.models.Teacher;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class TeacherServiceTests {

    private MockedStatic<TeacherDb> mockedTeacherDb;

    private TeacherService service;

    @BeforeEach
    public void setup(){
        mockedTeacherDb = mockStatic(TeacherDb.class);
        service = new TeacherService();
    }


    @Test
    public void testGetTeachers() throws SQLException {
        // Arrange
        var teachers = List.of(
                new Teacher("TEST_TEACHER_1", "TEST_PHONE", "TEST_EMAIL"),
                new Teacher("TEST_TEACHER_2", "TEST_PHONE", "TEST_EMAIL")
        );
        mockedTeacherDb.when(TeacherDb::findAll).thenReturn(teachers);

        // Act
        var result = service.getTeachers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("TEST_TEACHER_1", result.getFirst().getTeacherName());
    }


    @Test
    public void testGetTeacherNames() throws SQLException {
        // Arrange
        var teachers = List.of(
                new Teacher("TEST_TEACHER_1", "TEST_PHONE", "TEST_EMAIL"),
                new Teacher("TEST_TEACHER_2", "TEST_PHONE", "TEST_EMAIL")
        );
        mockedTeacherDb.when(TeacherDb::findAll).thenReturn(teachers);

        // Act
        var result = service.getTeacherNames();

        // Assert
        assertEquals("TEST_TEACHER_1, TEST_TEACHER_2", result);
    }

    @AfterEach
    public void tearDown(){
        mockedTeacherDb.close();
    }
}
