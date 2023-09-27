package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int,
    val name: String,
    val mobileNo: String
)
