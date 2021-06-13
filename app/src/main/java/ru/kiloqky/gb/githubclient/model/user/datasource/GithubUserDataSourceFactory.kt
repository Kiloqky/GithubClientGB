package ru.kiloqky.gb.githubclient.model.user.datasource

import ru.kiloqky.gb.githubclient.model.api.GithubApiFactory
import ru.kiloqky.gb.githubclient.model.user.datasource.cloud.CloudGithubUserDataSource

object GithubUserDataSourceFactory {
    fun create(): GithubUserDataSource = CloudGithubUserDataSource(GithubApiFactory.create())
}