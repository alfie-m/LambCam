package com.example.lambcamv1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lambcamv1.models.Tup

@Database(entities = [Tup::class], version = 1, exportSchema = false)
abstract class TupDatabase: RoomDatabase() {

    // Connect tup database and tup data access object
    abstract fun tupDao(): TupDao

    companion object{
        @Volatile
        private var INSTANCE: TupDatabase? = null

        fun getDatabase(context: Context): TupDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TupDatabase::class.java,
                    "tup_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}