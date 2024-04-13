package edu.com.trailslist.database.implementation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.database.dao.TrailDao
import edu.com.trailslist.database.provider.TrailDaoProvider

@Database(
    entities = [Trail::class],
    version = 2
)
abstract class TrailDatabase: RoomDatabase() {
    abstract fun trailDao(): TrailDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE trails ADD COLUMN measuredTime INTEGER NOT NULL DEFAULT 0")
            }
        }
        fun getDatabaseInstance(context: Context): TrailDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TrailDatabase::class.java,
                "trails.db"
            ).addMigrations(MIGRATION_1_2)
                .build()
        }
    }

    object TrailDatabaseProvider : TrailDaoProvider {
        private lateinit var trailDao: TrailDao

        fun initialize(context: Context) {
            trailDao = getDatabaseInstance(context).trailDao()
        }

        override fun provideTrailDao(): TrailDao {
            return trailDao
        }
    }
}