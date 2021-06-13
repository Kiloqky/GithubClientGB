package ru.kiloqky.gb.githubclient.model.storage.dao


import androidx.room.*
import io.reactivex.rxjava3.core.*
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.entities.Repo

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM github_users")
    fun loadUsers(): Observable<List<GithubUser>>

    @Query("SELECT * FROM github_users WHERE login LIKE :login LIMIT 1")
    fun loadUserByLogin(login: String): Single<GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainUser(users: List<GithubUser>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retainUser(user: GithubUser): Completable
}