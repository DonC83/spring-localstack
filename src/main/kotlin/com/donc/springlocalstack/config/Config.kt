package com.donc.springlocalstack.config

import com.donc.springlocalstack.model.Secret
import com.donc.springlocalstack.repository.SecretService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config(
    secretService: SecretService,
    @Value("\${client.secret.name}") private val clientSecretName: String
) {

    private val secret = secretService
        .getSecretValue(clientSecretName, Secret::class.java).secret
        .let { secret ->
            if (secret.equals("funnyman")) {
                "alternative"
            } else {
                secret
            }
        }

    @Bean
    fun getSecret(): String {
        return secret
    }
}
