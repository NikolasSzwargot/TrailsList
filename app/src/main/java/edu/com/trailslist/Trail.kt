package edu.com.trailslist

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trail(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)