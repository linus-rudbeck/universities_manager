package se.distansakademin.data;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DynamoDb {

    private final static String TABLE_NAME = "CourseInfo";
    private final static String KEY_ATTRIBUTE_NAME = "CourseInfoID";

    private DynamoDbClient dynamoDbClient = null;

    public DynamoDb() {
        this.dynamoDbClient = getConnection();
        createTable();
    }

    private DynamoDbClient getConnection(){
        return DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    private void createTable(){
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

        System.out.println("Tabellen skapad");
    }


}
