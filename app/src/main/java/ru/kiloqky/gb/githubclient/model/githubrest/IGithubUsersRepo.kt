package ru.kiloqky.gb.githubclient.model.githubrest

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.githubclient.model.githubrest.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.githubrest.entities.Repo

interface IGithubUsersRepo {
    fun loadUsers(): Single<List<GithubUser>>

    fun loadUser(login: String): Single<GithubUser>

    fun loadReposFromLogin(login: String): Single<List<Repo>>

    fun loadReposFromUrl(url: String): Single<List<Repo>>

    fun loadRepo(repoName: String, repoOwner: String): Single<Repo>
}