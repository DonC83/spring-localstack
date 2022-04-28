package com.donc.springlocalstack.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException

class SecretClient(
    private val secretsManagerClient: SecretsManagerClient
) {
    private val logger: Logger
        get() = LoggerFactory.getLogger(javaClass)

    fun <T> getSecretValue(secretName: String, valueType: Class<T>): T {
        try {
            val valueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build()
            val valueResponse = secretsManagerClient.getSecretValue(valueRequest)
            return jacksonObjectMapper().readValue(valueResponse.secretString(), valueType)
        } catch (e: SecretsManagerException) {
            e.awsErrorDetails()?.errorMessage()?.let { message ->
                logger.error(message, e)
            }
            throw e
        }
    }
}
