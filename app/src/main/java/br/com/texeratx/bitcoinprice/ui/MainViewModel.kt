package br.com.texeratx.bitcoinprice.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import br.com.texeratx.bitcoinprice.repository.ValueRepository
import br.com.texeratx.bitcoinprice.room.getDatabase
import com.anychart.chart.common.dataentry.ValueDataEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val repository = ValueRepository(database)

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var values = repository.values

    var latestValue = Transformations.map(values) {
        it.maxByOrNull { value -> value.time }!!
    }

    var chartValues = Transformations.map(values) {
        it.map { value -> ValueDataEntry(value.formattedTime, value.value) }
    }

    init {
        viewModelScope.launch {
            repository.refreshValues()
        }
    }
}

