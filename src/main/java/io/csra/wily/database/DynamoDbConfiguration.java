package io.csra.wily.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.context.annotation.Bean;

public class DynamoDbConfiguration {

    @Bean
    public AmazonDynamoDB amazonDynamoDbClient() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    @Bean
    public DynamoDB dynamoDb(AmazonDynamoDB amazonDynamoDBClient) {
        return new DynamoDB(amazonDynamoDBClient);
    }
}
