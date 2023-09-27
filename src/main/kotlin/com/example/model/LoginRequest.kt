package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
   val userName: String,
    val mobileNo: String
)
