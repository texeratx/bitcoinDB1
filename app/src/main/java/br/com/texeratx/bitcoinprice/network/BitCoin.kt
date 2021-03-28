package br.com.texeratx.bitcoinprice.network

import br.com.texeratx.bitcoinprice.model.Value
import br.com.texeratx.bitcoinprice.room.DatabaseValue
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BitCoin(
    val status: String,
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<NetworkValue>
)

/*fun BitCoin.asModel(): List<Value> {
    return values.map {
        Value(
            time = it.time,
            value = it.value
        )
    }
}*/

fun BitCoin.asDatabaseModel(): Array<DatabaseValue> {
    return values.map {
        DatabaseValue(
            time = it.time,
            value = it.value
        )
    }.toTypedArray()
}