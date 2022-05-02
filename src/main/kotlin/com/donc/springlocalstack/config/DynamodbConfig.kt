package com.donc.springlocalstack.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

@Configuration
class DynamodbConfig(
    @Value("\${aws.region}") private val region: String,
    @Value("\${aws.endpoint}") private val endpoint: String,
    private val credentialsProvider: AwsCredentialsProvider
) {

    @Bean
    fun dynamoDbEnhancedAsyncClient(): DynamoDbEnhancedAsyncClient {
        val client = DynamoDbAsyncClient.builder()
            .region(Region.of(region))
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(credentialsProvider)
            .build()

        return DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(client)
            .build()
    }
}
