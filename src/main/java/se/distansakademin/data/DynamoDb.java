package se.distansakademin.data;

import se.distansakademin.models.CourseInfo;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.UUID;

public class DynamoDb {

    private final static String TABLE_NAME = "CourseInfo";
    private final static String KEY_ATTRIBUTE_NAME = "CourseInfoID";

    private DynamoDbClient dynamoDbClient = null;

    public DynamoDb() {
        this.dynamoDbClient = getConnection();

        try {
            createTable();
        } catch (ResourceInUseException e) {
            System.out.println("Table exists: " + TABLE_NAME);
        }
    }

    private DynamoDbClient getConnection() {
        return DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    private void createTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName(KEY_ATTRIBUTE_NAME)
                                .attributeType(ScalarAttributeType.S)
                                .build()
                )
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName(KEY_ATTRIBUTE_NAME)
                                .keyType(KeyType.HASH)
                                .build()
                )
                .provisionedThroughput(
                        ProvisionedThroughput.builder()
                                .readCapacityUnits(5L)
                                .writeCapacityUnits(5L)
                                .build()
                )
                .tableName(TABLE_NAME)
                .build();

        dynamoDbClient.createTable(request);

        System.out.println("Created table: " + TABLE_NAME);
    }



    public void saveCourseInfo(CourseInfo courseInfo) {
        HashMap<String, AttributeValue> courseInfoMap = new HashMap<>();

        // Universally Unique IDentifier
        var uuid = UUID.randomUUID().toString();

        courseInfoMap.put(KEY_ATTRIBUTE_NAME, AttributeValue.builder().s(uuid).build());

        String courseId = String.valueOf(courseInfo.getCourseId());
        courseInfoMap.put("CourseID", AttributeValue.builder().s(courseId).build());
        courseInfoMap.put("CourseName", AttributeValue.builder().s(courseInfo.getCourseName()).build());
        courseInfoMap.put("UniversityName", AttributeValue.builder().s(courseInfo.getUniversityName()).build());
        courseInfoMap.put("TeacherName", AttributeValue.builder().s(courseInfo.getTeacherName()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(courseInfoMap)
                .build();

        dynamoDbClient.putItem(request);
    }
}
