package br.com.texeratx.bitcoinprice.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDate(timeStamp: Long): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(timeStamp * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun getCompleteDate(timeStamp: Long): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
            val netDate = Date(timeStamp * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}