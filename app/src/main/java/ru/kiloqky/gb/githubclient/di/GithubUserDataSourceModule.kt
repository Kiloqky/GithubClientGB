package ru.kiloqky.gb.githubclient.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.model.api.GithubApi
import ru.kiloqky.gb.githubclient.model.storage.GithubStorage
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSourceImpl
import ru.kiloqky.gb.githubclient.model.user.datasource.cloud.CloudGithubUserDataSource
import javax.inject.Singleton

@Module
class GithubUserDataSourceModule {

    @Provides
    fun cloud(githubApi: GithubApi): GithubUserDataSource =
        CloudGithubUserDataSource(githubApi)

    @Singleton
    @Provides
    fun cache(database: GithubStorage): CacheGithubUserDataSource =
        CacheGithubUserDataSourceImpl(database)
}