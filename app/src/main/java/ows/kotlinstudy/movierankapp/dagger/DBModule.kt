package ows.kotlinstudy.movierankapp.dagger

import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.MovieApplication
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
}