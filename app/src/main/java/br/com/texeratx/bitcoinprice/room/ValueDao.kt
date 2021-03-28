package br.com.texeratx.bitcoinprice.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg value: DatabaseValue)

    @Query("SELECT * FROM value ORDER BY time DESC LIMIT 1")
    fun getLastValue(): LiveData<DatabaseValue>

    @Query("SELECT * FROM value")
    fun getValues(): LiveData<List<DatabaseValue>>
}