package com.example.lambcamv1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lambcamv1.models.Lamb

@Database(entities = [Lamb::class], version = 1, exportSchema = false)
abstract class LambDatabase: RoomDatabase() {

    // Connect lamb database and lamb data access object
    abstract fun lambDao(): LambDao

    companion object{
        @Volatile
        private var INSTANCE: LambDatabase? = null

        fun getDatabase(context: Context): LambDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LambDatabase::class.java,
                    "lamb_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}