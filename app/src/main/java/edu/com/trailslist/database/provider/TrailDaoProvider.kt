package edu.com.trailslist.database.provider

import edu.com.trailslist.database.dao.TrailDao

interface TrailDaoProvider {
    fun provideTrailDao(): TrailDao
}