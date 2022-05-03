package com.donc.springlocalstack.repository

import com.donc.springlocalstack.model.Account
import com.donc.springlocalstack.model.Secret
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Repository
class AccountRepository(
    client: DynamoDbEnhancedAsyncClient,
    secretService: SecretService,
    @Value("\${client.secret.name}") private val clientSecretName: String
) {


    private val secret = {
        secretService
            .getSecretValue(clientSecretName, Secret::class.java).secret
            .let { secret ->
                println("$$$$ $secretService")
                if (secret == "funnyman") {
                    "alternative"
                } else {
                    secret
                }
            }
    }

    private val table = client.table("Account", TableSchema.fromBean(Account::class.java))

    fun save(account: Account) {
        println(secret)
        table.putItem(account)
    }
}
