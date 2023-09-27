package com.example.dao.userDao

import com.example.DataBaseFactory.dbQuery
import com.example.model.UserData
import com.example.tables.UserDetails
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserDaoImpl : UserDao {

    private fun resultRowToUser(row: ResultRow) = UserData(
        id = row[UserDetails.id],
        name = row[UserDetails.name],
        mobileNo = row[UserDetails.mobileNo]
    )

//    override suspend fun allUsers(): List<UserData> = dbQuery {
//        UserDetails.selectAll().map(::resultRowToUser)
//    }
//
//    override suspend fun user(id: Int): UserData = dbQuery {
//        UserDetails.select {
//            UserDetails.id eq id
//        }.map(::resultRowToUser).single()
//    }
//
//    override suspend fun addNewUser(name: String, mobileNo: String): UserData = dbQuery {
//        val insertStatement = UserDetails.insert { userDetails ->
//            userDetails[UserDetails.name] = name
//            userDetails[UserDetails.mobileNo] = mobileNo
//        }
//        insertStatement.resultedValues!!.single().let (::resultRowToUser)
//    }
//
//    override suspend fun deleteUser(id: Int): Boolean  = dbQuery {
//       UserDetails.deleteWhere{ UserDetails.id eq id } > 0
//    }

    override suspend fun loginValidation(name: String, mobileNo: String): UserData?  = dbQuery {
       val query = UserDetails.selectAll()
        name.let {
            query.andWhere { UserDetails.name eq it }
        }
        mobileNo.let {
            query.andWhere { UserDetails.mobileNo eq it }
        }
        if (query.empty()){
            null
        }else {
            query.map(::resultRowToUser).single()
        }
    }
}