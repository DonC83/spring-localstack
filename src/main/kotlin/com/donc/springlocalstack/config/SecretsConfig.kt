package com.donc.springlocalstack.config

import com.donc.springlocalstack.repository.SecretClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@Configuration
class SecretsConfig(
    private val credentialsProvider: AwsCredentialsProvider,
) {
    @Bean
    fun secretsClient(@Value("\${aws.region}") region: String): SecretClient =
        SecretsManagerClient.builder()
            .region(Region.of(region))
            .credentialsProvider(credentialsProvider)
            .build()
            .let(::SecretClient)
}