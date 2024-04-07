package edu.com.trailslist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailDao {

    @Upsert
    suspend fun upsertTrail(trail: Trail)

    @Delete
    suspend fun deleteTrail(trail: Trail)

    @Query("SELECT * FROM trails ORDER BY name ASC")
    fun getTrailsOrderedByName(): Flow<List<Trail>>

    @Query("DELETE FROM trails")
    suspend fun deleteAllTrails()
}