package com.example.plugins

import com.example.model.AssetDataRequest
import com.example.model.PostAssetDataResult
import com.example.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60
        }
    }
    install(Authentication) {
        session<UserSession>("auth-session") {
            validate { session ->
                if(session.name != "") {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respond(PostAssetDataResult(response = false, isValidRequest = false))
            }
        }
    }
}
