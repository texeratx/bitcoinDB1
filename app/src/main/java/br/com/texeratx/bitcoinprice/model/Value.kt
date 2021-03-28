package br.com.texeratx.bitcoinprice.model

import br.com.texeratx.bitcoinprice.utils.DateUtils
import java.text.NumberFormat
import java.util.*

data class Value(
    val time: Long,
    val value: Double
) {
    val formattedTime: String
        get() {
            return DateUtils.getDate(time)
        }

    val formattedValue: String
        get() {
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            return numberFormat.format(value)
        }
}
