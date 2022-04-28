package com.donc.springlocalstack.model

data class Auth0Secret(
    val domain: String,
    val clientId: String,
    val secret: String,
    val scopes: Collection<String>?,
)
