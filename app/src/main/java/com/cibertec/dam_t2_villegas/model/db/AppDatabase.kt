package com.cibertec.dam_t2_villegas.model.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cibertec.dam_t2_villegas.model.Reminder
import com.cibertec.dam_t2_villegas.model.User
import com.cibertec.dam_t2_villegas.model.dao.ReminderDao
import com.cibertec.dam_t2_villegas.model.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Reminder::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "t2exam.db"

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {

                lateinit var prebuilt: AppDatabase

                prebuilt = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                prebuilt.userDao().clearUsers()
                                prebuilt.userDao().insertAll(User.getUsuerios())
                            }
                        }
                    })
                    .build()

                INSTANCE = prebuilt
                return prebuilt
            }
        }
    }
}