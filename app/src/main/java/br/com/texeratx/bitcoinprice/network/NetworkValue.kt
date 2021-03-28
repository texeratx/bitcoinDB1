package br.com.texeratx.bitcoinprice.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkValue(
    @Json(name = "x")
    val time: Long,
    @Json(name = "y")
    val value: Double

)
