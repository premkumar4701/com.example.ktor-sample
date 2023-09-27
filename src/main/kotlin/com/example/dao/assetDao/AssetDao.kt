package com.example.dao.assetDao

import com.example.model.AssetDataRequest
import com.example.model.PostAssetDataResult

interface AssetDao {

    suspend fun postAssetData(assetDataRequest: AssetDataRequest): PostAssetDataResult

    suspend fun getAllAssetData() : List<AssetDataRequest>
}