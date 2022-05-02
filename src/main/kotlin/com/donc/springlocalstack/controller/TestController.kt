package com.donc.springlocalstack.controller

import com.donc.springlocalstack.model.Secret
import com.donc.springlocalstack.repository.SecretService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val secretService: SecretService,
) {

    @PostMapping("/secret", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(secret: Secret) {
        secretService.putSecretValue(secret)
    }

    @GetMapping("/secret/{secretName}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSecret(@PathVariable secretName: String): String {
        println("Getting secret $secretName")
        val secret = secretService.getSecretValue(secretName, Secret::class.java)
        return secret.toString()
    }
}
