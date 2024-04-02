package edu.com.trailslist

import androidx.annotation.DrawableRes

data class Trail(
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)