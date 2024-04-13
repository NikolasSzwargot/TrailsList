package edu.com.trailslist.database.entities

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trails")
data class Trail(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val measuredTime: Long
)