package com.example

import com.example.tables.AssetDetails
import com.example.tables.UserDetails
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {
    fun init(){
        val  dataBase = Database.connect(
            url = "jdbc:postgresql://localhost:5433/",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "123"
        )
        transaction(dataBase){
            SchemaUtils.create(UserDetails)
            SchemaUtils.create(AssetDetails)
        }

    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO){
            block()
        }
}