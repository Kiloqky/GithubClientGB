package ru.kiloqky.gb.githubclient.model.storage

import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kiloqky.gb.githubclient.App

object GithubStorageFactory {

    private val inMemoryGithubStorage: GithubStorage by lazy {
        Room.databaseBuilder(
            App.ContextHolder.context,
            GithubStorage::class.java,
            "github_users"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun create(): GithubStorage = inMemoryGithubStorage
}