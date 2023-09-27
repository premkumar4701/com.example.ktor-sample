package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AssetDataRequest(
    val id: Int,
    val assetName: String,
    val ownerName: String,
    val brandName: String,
    val notes: String?
)

@Serializable
data class AssetDataList(
    val assetList: List<AssetDataRequest>
)
