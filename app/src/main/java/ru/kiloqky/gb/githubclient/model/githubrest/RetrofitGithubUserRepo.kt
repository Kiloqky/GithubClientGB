package ru.kiloqky.gb.githubclient.model.githubrest

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kiloqky.gb.githubclient.model.githubrest.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.githubrest.entities.Repo

class RetrofitGithubUserRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun loadUsers(): Single<List<GithubUser>> =
        api.loadUsers().subscribeOn(Schedulers.io())

    override fun loadUser(login: String): Single<GithubUser> =
        api.loadUser(login).subscribeOn(Schedulers.io())

    override fun loadReposFromLogin(login: String): Single<List<Repo>> =
        api.loadUserReposFromLogin(login).subscribeOn(Schedulers.io())

    override fun loadReposFromUrl(url: String): Single<List<Repo>> =
        api.loadUserReposFromUrl(url).subscribeOn(Schedulers.io())

    override fun loadRepo(repoName: String, repoOwner: String): Single<Repo> =
        api.loadRepo(repoName, repoOwner).subscribeOn(Schedulers.io())
}