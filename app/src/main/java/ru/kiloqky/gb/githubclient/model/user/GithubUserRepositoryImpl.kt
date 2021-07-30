package ru.kiloqky.gb.githubclient.model.user

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource
import ru.kiloqky.gb.githubclient.model.user.datasource.cache.CacheGithubUserDataSource
import java.util.concurrent.TimeUnit

class GithubUserRepositoryImpl(
    private val cloudUserDataSource: GithubUserDataSource,
    private val cacheUserDataSource: CacheGithubUserDataSource
) : GithubUserRepository {
    override fun loadUsers(): Observable<List<GithubUser>> =
        cacheUserDataSource
            .loadUsers()
            .flatMap(::loadUsersFromCloudIfRequired)


    private fun loadUsersFromCloudIfRequired(users: List<GithubUser>): Observable<List<GithubUser>> =
        if (users.isEmpty()) {
            cloudUserDataSource
                .loadUsers()
                .flatMapSingle(cacheUserDataSource::retain)
        } else {
            Observable.just(users)
        }


    override fun loadUserByLogin(login: String): Observable<GithubUser> =
        Observable.concat(
            cacheUserDataSource
                .loadUserByLogin(login)
                .toObservable(),
            cloudUserDataSource
                .loadUserByLogin(login)
                .flatMap(cacheUserDataSource::retain)
                .retryWhen { error -> error.delay(5L, TimeUnit.SECONDS) }
                .toObservable()
        )


    override fun loadReposFromLogin(login: String): Single<List<Repo>> =
        cloudUserDataSource
            .loadReposFromLogin(login)


    override fun loadReposFromUrl(url: String): Single<List<Repo>> =
        cloudUserDataSource
            .loadReposFromUrl(url)


    override fun loadRepo(repoName: String, repoOwner: String): Single<Repo> =
        cloudUserDataSource.loadRepo(repoName, repoOwner)


}