package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.model.api.GithubApi
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cloud.CloudGithubUserDataSource

@Module
class GithubUserDataSourceModule {

    @Provides
    fun cloudGithubUserDataSource(githubApi: GithubApi): GithubUserDataSource =
        CloudGithubUserDataSource(githubApi)

}