package com.donc.springlocalstack.repository

import com.donc.springlocalstack.model.Account
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Repository
class AccountRepository(
    client: DynamoDbEnhancedAsyncClient
) {

    private val table = client.table("Account", TableSchema.fromBean(Account::class.java))

    fun save(account: Account) {
        table.putItem(account)
    }
}
