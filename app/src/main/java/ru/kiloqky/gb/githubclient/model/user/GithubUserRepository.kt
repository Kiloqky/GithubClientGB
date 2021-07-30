package ru.kiloqky.gb.githubclient.model.user

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo

interface GithubUserRepository {
    fun loadUsers(): Observable<List<GithubUser>>

    fun loadUserByLogin(login: String): Observable<GithubUser>

    fun loadReposFromLogin(login: String): Single<List<Repo>>

    fun loadReposFromUrl(url: String): Single<List<Repo>>

    fun loadRepo(repoName: String, repoOwner: String): Single<Repo>
}