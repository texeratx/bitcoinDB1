package br.com.texeratx.bitcoinprice.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.texeratx.bitcoinprice.repository.ValueRepository
import br.com.texeratx.bitcoinprice.room.getDatabase
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

    init {
        viewModelScope.launch {
            repository.refreshValues()
        }
    }
}

