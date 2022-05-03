package com.donc.springlocalstack.config

import com.donc.springlocalstack.model.Secret
import com.donc.springlocalstack.repository.SecretService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config(
    private val secretService: SecretService,
    @Value("\${client.secret.name}") private val clientSecretName: String,
) {

    @Bean
    fun secretVal() : String {
        val secret = secretService
            .getSecretValue(clientSecretName, Secret::class.java).secret
            .let { secret ->
                println("$$$$ $secretService")
                if (secret == "funnyman") {
                    "alternative"
                } else {
                    secret
                }
            }
        return secret
    }

    @Bean
    fun getSecret(secretVal: String): String {
        println("##### $secretVal")
        return secretVal
    }
}
