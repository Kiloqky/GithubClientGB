package ru.kiloqky.gb.githubclient.di

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.model.storage.GithubStorage
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSourceImpl
import javax.inject.Singleton

@Module
class GithubStorageModule {

    @Singleton
    @Provides
    fun database(app: App) =
        Room.databaseBuilder(
            app,
            GithubStorage::class.java,
            "github_users"
        )
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun cache(database: GithubStorage): CacheGithubUserDataSource =
        CacheGithubUserDataSourceImpl(database)

}