package com.donc.springlocalstack.repository

import com.donc.springlocalstack.model.Secret
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import software.amazon.awssdk.services.secretsmanager.model.PutSecretValueRequest
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException

class SecretService(
    private val secretsManagerClient: SecretsManagerClient,
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

    fun putSecretValue(secret: Secret) {
        try {
            val request = PutSecretValueRequest.builder()
                .secretId(secret.name)
                .secretString("{ \"clientId\" : \"${secret.clientId}\", \"secret\" : \"${secret.secret}\" ")
                .build()
            secretsManagerClient.putSecretValue(request)
        } catch (e: SecretsManagerException) {
            e.awsErrorDetails()?.errorMessage()?.let { message ->
                logger.error(message, e)
            }
        }
    }
}
