package br.com.texeratx.bitcoinprice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.texeratx.bitcoinprice.model.Value
import br.com.texeratx.bitcoinprice.network.Network
import br.com.texeratx.bitcoinprice.network.asDatabaseModel
import br.com.texeratx.bitcoinprice.room.AppDatabase
import br.com.texeratx.bitcoinprice.room.asModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValueRepository(private val database: AppDatabase) {

    val values: LiveData<List<Value>> = Transformations.map(database.valueDao().getValues()) {
        it.asModel()
    }

    suspend fun refreshValues() {
        withContext(Dispatchers.IO) {
            val values = Network.apiBitCoin.getBitCoinValuesAsync("1days").await()
            database.valueDao().insertAll(*values.asDatabaseModel())
        }
    }
}
