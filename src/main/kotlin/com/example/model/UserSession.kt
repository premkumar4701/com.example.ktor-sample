package com.example.model

import io.ktor.server.auth.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserSession(val name: String, val count: Int) : Principal

@Serializable
data class PostAssetDataResult(
    val response: Boolean,
    val isValidRequest: Boolean
)
