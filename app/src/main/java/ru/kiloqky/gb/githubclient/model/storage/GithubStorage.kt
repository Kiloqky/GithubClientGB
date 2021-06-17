package ru.kiloqky.gb.githubclient.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.storage.dao.GithubUserDao

@Database(version = 1, entities = [GithubUser::class])
abstract class GithubStorage : RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao
}