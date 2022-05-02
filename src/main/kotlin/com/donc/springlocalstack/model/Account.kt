package com.donc.springlocalstack.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Account(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute(value = "ID") var id: String? = null,
    @get:DynamoDbAttribute(value = "Name") var name: String? = null
)
