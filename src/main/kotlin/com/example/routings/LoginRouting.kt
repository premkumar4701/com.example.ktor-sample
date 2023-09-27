package com.example.routings

import com.example.dao.userDao.UserDao
import com.example.model.LoginRequest
import com.example.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.loginRouting(dao: UserDao) {
    routing {

        post("/loginRequest") {
            val formParameters = call.receive<LoginRequest>()
            val userInfo = dao.loginValidation(formParameters.userName, formParameters.mobileNo)
            if (userInfo != null) {
                UserIdPrincipal(userInfo.name)
                call.sessions.set(UserSession(name = userInfo.name, count = 1))
                call.respond(userInfo)
            }
        }

        authenticate("auth-session") {
            get("/hello") {
                val userSession = call.principal<UserSession>()
                if (userSession != null) {
                    val count = userSession.count.plus(1)
                    call.sessions.set(userSession.copy(count = count))
                    call.respondText("Hello, ${userSession.name}! Visit count is ${userSession.count}.")
                }
            }
        }

        get("/clearSession") {
            call.sessions.clear<UserSession>()
            call.respondText("session cleared!!")
        }

    }
}