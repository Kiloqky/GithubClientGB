package ru.kiloqky.gb.githubclient.model.user.datasource.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.user.datasource.GithubUserDataSource

interface CacheGithubUserDataSource : GithubUserDataSource {

    fun retain(users: List<GithubUser>): Single<List<GithubUser>>

    fun retain(user: GithubUser): Single<GithubUser>

}