package com.example.employmenttest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    // Add all the entities table inside in entities array
    entities = [ChannelModel::class],
    // Update the version after any sql change
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    companion object {

        // Database Name
        private const val DB_NAME = "Test"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .addCallback(
                object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            instance?.channelDao()?.insert(DATA)
                        }
                    }
                })
            .build()


        val DATA = listOf(
            ChannelModel("Channel 1", true),
            ChannelModel("Channel 2", true),
            ChannelModel("Channel 3", true)
        )
    }


    // Add here all Dao Classes here
    abstract fun channelDao(): ChannelDao
}