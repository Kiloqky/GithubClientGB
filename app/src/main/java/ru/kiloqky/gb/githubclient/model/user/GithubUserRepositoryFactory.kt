package ru.kiloqky.gb.githubclient.model.user

import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSourceFactory
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSourceFactory

object GithubUserRepositoryFactory {

    private val userRepository: GithubUserRepository by lazy {
        GithubUserRepositoryImpl(
            GithubUserDataSourceFactory.create(),
            CacheGithubUserDataSourceFactory.create()
        )
    }

    fun create(): GithubUserRepository = userRepository
}