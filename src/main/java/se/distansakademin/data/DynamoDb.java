package se.distansakademin.data;

import se.distansakademin.models.CourseInfo;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

public class DynamoDb {

    private final static String TABLE_NAME = "CourseInfo";
    private final static String KEY_ATTRIBUTE_NAME = "CourseInfoID";

    private static DynamoDbClient dynamoDbClient = null;

    public DynamoDb() {
        try {
            createTable();
        } catch (ResourceInUseException e) {
            System.out.println("Table exists: " + TABLE_NAME);
        }
    }


    private static DynamoDbClient getClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.builder()
                    .region(Region.EU_NORTH_1)
                    .build();
        }

        return dynamoDbClient;
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

        getClient().createTable(request);

        System.out.println("Created table: " + TABLE_NAME);
    }


    /**
     * Saves a CourseInfo object to DynamoDB
     * @param courseInfo The information to be saved
     * @return True if save successful
     */
    public boolean save(CourseInfo courseInfo) {

        HashMap<String, AttributeValue> courseInfoMap = new HashMap<>();

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

        try{
            dynamoDbClient.putItem(request);
        } catch (AwsServiceException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



    public List<CourseInfo> findAll() {

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = getClient().scan(scanRequest);

        List<CourseInfo> courseInfoList = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            CourseInfo courseInfo = new CourseInfo();

            String courseInfoId = item.get(KEY_ATTRIBUTE_NAME).s();
            courseInfo.setCourseInfoId(courseInfoId);

            int courseId = Integer.parseInt(item.get("CourseID").s());
            courseInfo.setCourseId(courseId);

            String courseName = item.get("CourseName").s();
            courseInfo.setCourseName(courseName);

            String universityName = item.get("UniversityName").s();
            courseInfo.setUniversityName(universityName);

            String teacherName = item.get("TeacherName").s();
            courseInfo.setTeacherName(teacherName);

            courseInfoList.add(courseInfo);
        }

        return courseInfoList;
    }


    public void emptyTable() {
        List<CourseInfo> courseInfoList = findAll();
        courseInfoList.forEach(courseInfo -> delete(courseInfo.getCourseInfoId()));
    }


    public void delete(String key){
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(KEY_ATTRIBUTE_NAME, AttributeValue.fromS(key)))
                .build();

        getClient().deleteItem(request);
    }

}
