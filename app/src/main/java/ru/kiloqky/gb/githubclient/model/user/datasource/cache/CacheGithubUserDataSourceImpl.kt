package ru.kiloqky.gb.githubclient.model.user.datasource.cache

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kiloqky.gb.githubclient.model.storage.GithubStorage
import ru.kiloqky.gb.githubclient.model.storage.dao.GithubUserDao
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo

class CacheGithubUserDataSourceImpl(githubStorage: GithubStorage) : CacheGithubUserDataSource {

    private val githubUserDao: GithubUserDao = githubStorage.githubUserDao()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        githubUserDao
            .retainUser(users)
            .andThen(loadUsers())
            .firstOrError()


    override fun retain(user: GithubUser): Single<GithubUser> =
        githubUserDao
            .retainUser(user)
            .andThen(user.login?.let { loadUserByLogin(it) })


    override fun loadUsers(): Observable<List<GithubUser>> =
        githubUserDao
            .loadUsers()

    override fun loadUserByLogin(login: String): Single<GithubUser> =
        githubUserDao
            .loadUserByLogin(login)
            .observeOn(Schedulers.io())

    override fun loadReposFromLogin(login: String): Single<List<Repo>> {
        return Single.just(null)
    }


    override fun loadReposFromUrl(url: String): Single<List<Repo>> {
        return Single.just(null)
    }

    override fun loadRepo(repoName: String, repoOwner: String): Single<Repo> {
        return Single.just(null)
    }

}