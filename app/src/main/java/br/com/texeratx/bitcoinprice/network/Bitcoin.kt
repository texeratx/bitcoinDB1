package br.com.texeratx.bitcoinprice.network

import br.com.texeratx.bitcoinprice.room.DatabaseValue
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bitcoin(
    val status: String,
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<NetworkValue>
)

fun Bitcoin.asDatabaseModel(): Array<DatabaseValue> {
    return values.map {
        DatabaseValue(
            time = it.time,
            value = it.value
        )
    }.toTypedArray()
}