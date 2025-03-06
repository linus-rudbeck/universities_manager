package se.distansakademin;

import se.distansakademin.data.DynamoDb;
import se.distansakademin.data.mysql.CourseInfoDb;
import se.distansakademin.data.mysql.TeacherDb;
import se.distansakademin.data.mysql.UniversityDb;
import se.distansakademin.models.CourseInfo;
import se.distansakademin.models.Teacher;
import se.distansakademin.models.University;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("""
                =================================
                - - -     Menu              - - -
                - - -  1. Show teachers     - - -
                - - -  2. Show universities - - -
                - - -  3. Show cache        - - -
                - - -  4. Reload cache      - - -
                =================================
                Input:""");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                showTeachers();
                break;
            case 2:
                showUniversities();
                break;
            case 3:
                showDynamoDbCourseInfo();
                break;
            case 4:
                loadCourseInfoToDynamoDb();
                break;
        }
    }

    private static void showTeachers() throws SQLException {
        List<Teacher> teachers = TeacherDb.findAll();
        teachers.forEach(System.out::println);
    }

    private static void showUniversities() throws SQLException {
        List<University> universities = UniversityDb.findAll();
        universities.forEach(System.out::println);
    }

    private static void showDynamoDbCourseInfo() throws SQLException {
        DynamoDb dynamoDb = new DynamoDb();
        List<CourseInfo> courseInfoList = dynamoDb.findAll();
        courseInfoList.forEach(System.out::println);
    }


    private static void loadCourseInfoToDynamoDb() throws SQLException {
        // MySQL
        List<CourseInfo> courseInfoList = CourseInfoDb.findAll();

        // DynamoDB
        DynamoDb dynamoDb = new DynamoDb();
        dynamoDb.emptyTable();
        courseInfoList.forEach(dynamoDb::save);

        System.out.println("Saved " + courseInfoList.size() + " courses to the cache!");
    }
}