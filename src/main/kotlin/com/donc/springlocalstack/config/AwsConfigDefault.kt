package com.donc.springlocalstack.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider

@Configuration
class AwsConfigDefault {
    @Bean
    fun credentialsProvider(): AwsCredentialsProvider =
        DefaultCredentialsProvider.builder().build()
}
