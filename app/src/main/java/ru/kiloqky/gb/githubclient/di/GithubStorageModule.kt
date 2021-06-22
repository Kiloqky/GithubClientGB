package ru.kiloqky.gb.githubclient.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.model.storage.GithubStorage
import javax.inject.Singleton

@Module
class GithubStorageModule {
    
    @Singleton
    @Provides
    fun database(context: Context) =
        Room.databaseBuilder(
            context,
            GithubStorage::class.java,
            "github_users"
        )
            .fallbackToDestructiveMigration()
            .build()
}