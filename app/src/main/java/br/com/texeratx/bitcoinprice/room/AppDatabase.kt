package br.com.texeratx.bitcoinprice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseValue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun valueDao(): ValueDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "values"
            ).build()
        }
    }
    return INSTANCE
}
