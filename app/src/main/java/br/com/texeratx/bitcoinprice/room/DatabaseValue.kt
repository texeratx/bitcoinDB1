package br.com.texeratx.bitcoinprice.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.texeratx.bitcoinprice.model.Value

@Entity(tableName = "value")
data class DatabaseValue(
    @PrimaryKey
    val time: Long,
    val value: Double
)

fun List<DatabaseValue>.asModel(): List<Value> {
    return map {
        Value(
            time = it.time,
            value = it.value
        )
    }
}
