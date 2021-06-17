package ru.kiloqky.gb.githubclient.model.user.datasource.cache

import ru.kiloqky.gb.githubclient.model.storage.GithubStorageFactory

object CacheGithubUserDataSourceFactory {

    private val cacheGithubUserDataSource: CacheGithubUserDataSource by lazy {
        CacheGithubUserDataSourceImpl(
            GithubStorageFactory.create()
        )
    }

    fun create(): CacheGithubUserDataSource = cacheGithubUserDataSource
}