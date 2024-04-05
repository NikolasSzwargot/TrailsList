package edu.com.trailslist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Trail::class],
    version = 1
)
abstract class TrailDatabase: RoomDatabase() {
    abstract val trailDao: TrailDao
}