package ru.kiloqky.gb.githubclient.model.githubrest.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    @Expose val name: String? = null,
    @Expose val html_url: String? = null,
    @Expose val forks: Int? = null,
    @Expose val watchers: Int? = null,
    @Expose val owner: GithubUser? = null
) : Parcelable