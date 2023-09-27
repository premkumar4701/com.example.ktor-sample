package com.example.tables

import org.jetbrains.exposed.sql.Table

object AssetDetails : Table(){
    val id = integer("id").autoIncrement()
    val assetName = varchar("asset_name",500)
    val ownerName = varchar("owner_name",500)
    val brandName = varchar("brand_name",500)
    val notes = varchar("additional_notes",800).nullable()

    override val primaryKey = PrimaryKey(id)
}