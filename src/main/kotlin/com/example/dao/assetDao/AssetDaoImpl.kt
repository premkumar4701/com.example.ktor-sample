package com.example.dao.assetDao

import com.example.DataBaseFactory.dbQuery
import com.example.model.AssetDataRequest
import com.example.model.PostAssetDataResult
import com.example.tables.AssetDetails
import org.jetbrains.exposed.sql.*

class AssetDaoImpl: AssetDao {

    override suspend fun postAssetData(assetDataRequest: AssetDataRequest): PostAssetDataResult = dbQuery {
        val exists = checkRowExist(assetDataRequest)
        if(!exists) {
        val resultCount =  AssetDetails.insert {
                it[id] = assetDataRequest.id
                it[assetName] = assetDataRequest.assetName
                it[brandName] = assetDataRequest.brandName
                it[ownerName] = assetDataRequest.ownerName
                it[notes] = assetDataRequest.notes
            }.insertedCount
            PostAssetDataResult(resultCount > 0,true)
        }else{
           val resultCount = AssetDetails.update ({ AssetDetails.id eq assetDataRequest.id }){
                it[assetName] = assetDataRequest.assetName
                it[brandName] = assetDataRequest.brandName
                it[ownerName] = assetDataRequest.ownerName
                it[notes] = assetDataRequest.notes
            }
            PostAssetDataResult(resultCount > 0,true)
        }
    }

    private fun checkRowExist(assetDataRequest: AssetDataRequest): Boolean{
        val resultRow = AssetDetails.select {
            AssetDetails.id eq assetDataRequest.id
        }.count()
        return resultRow > 0
    }

    override suspend fun getAllAssetData(): List<AssetDataRequest> = dbQuery {
        AssetDetails.selectAll().map(::resultRowToUser)
    }

    private fun resultRowToUser(row: ResultRow) = AssetDataRequest(
        id = row[AssetDetails.id],
        assetName = row[AssetDetails.assetName],
        ownerName = row[AssetDetails.ownerName],
        brandName = row[AssetDetails.brandName],
        notes = row[AssetDetails.notes]
    )
}