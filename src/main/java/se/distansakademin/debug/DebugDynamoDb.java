package se.distansakademin.debug;

import se.distansakademin.data.DynamoDb;
import se.distansakademin.models.CourseInfo;

import java.util.UUID;

public class DebugDynamoDb {
    public static void main(String[] args) {
        DynamoDb dynamoDb = new DynamoDb(); // CourseInfoDdb

        CourseInfo courseInfo = new CourseInfo("Databashantering", "Jensen YH", "Linkan");
        courseInfo.setCourseId(50_000);

        dynamoDb.saveCourseInfo(courseInfo);

        System.out.println("Saved course");
    }
}
