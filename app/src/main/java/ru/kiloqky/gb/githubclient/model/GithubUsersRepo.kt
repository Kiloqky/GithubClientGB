package ru.kiloqky.gb.githubclient.model

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

    fun getUsers(): List<GithubUser> {
        return repositories
    }

    fun getUserById(userId: String): GithubUser {
        return repositories.first{it.login == userId}
    }
}