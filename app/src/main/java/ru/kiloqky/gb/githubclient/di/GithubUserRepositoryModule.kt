package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepositoryImpl
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSource

@Module
class GithubUserRepositoryModule {

    @Provides
    fun userRepository(
        githubUserRepository: GithubUserDataSource,
        cacheGithubUserDataSource: CacheGithubUserDataSource
    ): GithubUserRepository =
        GithubUserRepositoryImpl(githubUserRepository, cacheGithubUserDataSource)

}