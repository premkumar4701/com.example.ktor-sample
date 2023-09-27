package com.example.dao.userDao

import com.example.model.UserData
import com.example.tables.UserDetails

interface UserDao {

//    suspend fun allUsers(): List<UserData>
//    suspend fun user(id: Int): UserData
//    suspend fun addNewUser(name: String, mobileNo: String): UserData
//    suspend fun deleteUser(id: Int): Boolean
    suspend fun loginValidation(name: String, mobileNo: String): UserData?

}