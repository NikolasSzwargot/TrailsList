package edu.com.trailslist

interface TrailDaoProvider {
    fun provideTrailDao(): TrailDao
}