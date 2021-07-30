package ru.kiloqky.gb.githubclient.model.user.datasource.cloud

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.githubclient.model.api.GithubApi
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource

class CloudGithubUserDataSource(private val githubApi: GithubApi) : GithubUserDataSource {
    override fun loadUsers(): Observable<List<GithubUser>> =
        githubApi.loadUsers()

    override fun loadUserByLogin(login: String): Single<GithubUser> =
        githubApi.loadUserByLogin(login)

    override fun loadReposFromLogin(login: String): Single<List<Repo>> =
        githubApi.loadUserReposFromLogin(login)

    override fun loadReposFromUrl(url: String): Single<List<Repo>> =
        githubApi.loadUserReposFromUrl(url)

    override fun loadRepo(repoName: String, repoOwner: String): Single<Repo> =
        githubApi.loadRepo(repoName, repoOwner)
}