package se.distansakademin.debug;

import se.distansakademin.data.DynamoDb;

public class DebugDynamoDb {
    public static void main(String[] args) {
        var dynamoDb = new DynamoDb();
        dynamoDb.emptyTable();
    }
}
