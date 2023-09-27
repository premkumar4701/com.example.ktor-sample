package com.example.tables

import org.jetbrains.exposed.sql.Table

object UserDetails : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",150)
    val mobileNo = varchar("mobileNo",20)

    override val primaryKey = PrimaryKey(id)
}

