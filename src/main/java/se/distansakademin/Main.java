package se.distansakademin;

import se.distansakademin.data.DynamoDb;
import se.distansakademin.data.mysql.CourseInfoDb;
import se.distansakademin.data.mysql.MysqlConnector;
import se.distansakademin.data.mysql.UniversityDb;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        var courseInfoList = CourseInfoDb.findAll();

        DynamoDb dynamoDb = new DynamoDb();

        // courseInfoList.forEach(dynamoDb::saveCourseInfo);

        courseInfoList.forEach(courseInfo ->{
            dynamoDb.saveCourseInfo(courseInfo);
        });

    }
}