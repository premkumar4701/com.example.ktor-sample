package com.example

import com.example.dao.assetDao.AssetDao
import com.example.dao.assetDao.AssetDaoImpl
import com.example.dao.userDao.UserDao
import com.example.dao.userDao.UserDaoImpl
import com.example.plugins.*
import com.example.routings.assetRouting
import com.example.routings.loginRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    configureSerialization()
    configureSecurity()

    val dao: UserDao = UserDaoImpl()
    val assetDao: AssetDao = AssetDaoImpl()
    DataBaseFactory.init()
    loginRouting(dao)
    assetRouting(assetDao)

}