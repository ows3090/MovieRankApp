package ows.kotlinstudy.movierankapp.dagger.module

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.MovieApplication
import ows.kotlinstudy.movierankapp.dagger.component.ActivityComponent
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import javax.inject.Singleton

@Module(subcomponents = [ActivityComponent::class])
class DBModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(movieApplication: MovieApplication): RoomDatabase {
        return Room.databaseBuilder(
            movieApplication.applicationContext,
            MovieDatabase::class.java,
            "Movie-Database"
        ).build()
    }

    @Provides
    fun provideRoomMigration(): Migration {
        return object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `Movie` (" +
                            "`actor` TEXT, " +
                            "`audience` INTEGER, " +
                            "`audience_rating` REAL, " +
                            "`date` TEXT, " +
                            "`director` TEXT, " +
                            "`dislike` INTEGER, " +
                            "`duration` INTEGER, " +
                            "`genre` TEXT, " +
                            "`grade` INTEGER, " +
                            "`id` INTEGER, " +
                            "`image` TEXT, " +
                            "`like` INTEGER, " +
                            "`outlinks` TEXT, " +
                            "`photos` TEXT, " +
                            "`reservation_grade` INTEGER, " +
                            "`reservation_rate` REAL, " +
                            "`reviewer_rating` REAL, " +
                            "`synopsis` TEXT, " +
                            "`thumb` TEXT, " +
                            "`title` TEXT, " +
                            "`user_rating` REAL, " +
                            "`videos` TEXT, " +
                            "PRIMARY KEY(`id`))"
                )
                database.execSQL(
                    "CREATE TABLE `Comment` (" +
                            "`contents` TEXT, " +
                            "`id` INTEGER, " +
                            "`movieId` INTEGER, " +
                            "`rating` REAL, " +
                            "`recommend` INTEGER, " +
                            "`time` TEXT, " +
                            "`timestamp` INTEGER, " +
                            "`writer` TEXT, " +
                            "`writer_image` TEXT, " +
                            "PRIMARY KEY(`id`))"
                )

            }
        }
    }
}