package com.example.routings

import com.example.dao.assetDao.AssetDao
import com.example.model.AssetDataList
import com.example.model.PostAssetDataResult
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.assetRouting(dao: AssetDao){
    routing {
        authenticate("auth-session") {
            post("/postAssetData"){
                val assetRequestList = call.receive<AssetDataList>()
                lateinit var result : PostAssetDataResult
                if (assetRequestList.isEmptyLocalDataSync){
                    result = PostAssetDataResult(response = true,isValidRequest = true)
                    call.respond(result)
                }else {
                    assetRequestList.assetList.forEach { assetRequest ->
                        result = dao.postAssetData(assetRequest)
                    }
                    call.respond(result)
                }
            }
        }

        authenticate("auth-session") {
            get("/allAssetData") {
                val result = dao.getAllAssetData()
                call.respond(result)
            }
        }
    }
}