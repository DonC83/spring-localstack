package com.donc.springlocalstack.config

import com.donc.springlocalstack.repository.SecretService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import java.net.URI

@Configuration
class SecretsConfig(
    @Value("\${aws.endpoint}") private val endpoint: String,
    private val credentialsProvider: AwsCredentialsProvider,
) {
    @Bean
    fun secretsClient(@Value("\${aws.region}") region: String): SecretService =
        SecretsManagerClient.builder()
            .region(Region.of(region))
            .credentialsProvider(credentialsProvider)
            .endpointOverride(URI.create(endpoint))
            .build()
            .let(::SecretService)
}
