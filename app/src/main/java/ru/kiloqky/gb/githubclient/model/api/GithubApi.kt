package ru.kiloqky.gb.githubclient.model.api

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo

interface GithubApi {
    @GET("/users")
    fun loadUsers(): Observable<List<GithubUser>>

    @GET("users/{login}")
    fun loadUserByLogin(@Path("login") login: String): Single<GithubUser>

    @GET("users/{login}/repos")
    fun loadUserReposFromLogin(@Path("login") login: String): Single<List<Repo>>

    @GET
    fun loadUserReposFromUrl(@Url url: String): Single<List<Repo>>

    @GET("repos/{repoOwner}/{repoName}")
    fun loadRepo(
        @Path("repoName") repoName: String,
        @Path("repoOwner") repoOwner: String
    ): Single<Repo>
}