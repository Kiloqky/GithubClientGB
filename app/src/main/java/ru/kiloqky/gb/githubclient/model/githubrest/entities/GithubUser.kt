package ru.kiloqky.gb.githubclient.model.githubrest.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose val id: Int? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val name: String? = null,
    @Expose val public_repos: Int? = null,
    @Expose val followers: Int? = null,
    @Expose val repos_url: String? = null
) : Parcelable
