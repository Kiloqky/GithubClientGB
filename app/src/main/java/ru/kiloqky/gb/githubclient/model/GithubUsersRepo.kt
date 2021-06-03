package ru.kiloqky.gb.githubclient.model

import io.reactivex.rxjava3.core.Single

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
        GithubUser("login7"),
        GithubUser("login8"),
        GithubUser("login9"),
        GithubUser("login10")
    )

    fun getUsers(): Single<List<GithubUser>> =
        Single.just(repositories)

    fun getUserById(userId: String): Single<GithubUser> =
        Single.fromCallable { repositories.first { it.login == userId } }

}