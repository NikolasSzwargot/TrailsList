package edu.com.trailslist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Trail::class],
    version = 1
)
abstract class TrailDatabase: RoomDatabase() {
    abstract fun trailDao(): TrailDao

    companion object {
        fun getDatabaseInstance(context: Context): TrailDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TrailDatabase::class.java,
                "trails.db"
            ).build()
        }
    }
}