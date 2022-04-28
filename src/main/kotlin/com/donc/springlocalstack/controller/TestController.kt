package com.donc.springlocalstack.controller

import com.donc.springlocalstack.model.Auth0Secret
import com.donc.springlocalstack.repository.SecretClient
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class TestController
(private val secretClient: SecretClient) {

    @GetMapping("/secret/{secretName}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSecret(@PathVariable secretName: String): String {
        val secret = secretClient.getSecretValue(secretName, Auth0Secret::class.java)
        return secret.toString()
    }
}
