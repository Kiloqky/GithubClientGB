package ru.kiloqky.gb.githubclient.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GithubUser::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "github_user_repos"
)
@Parcelize
data class Repo(
    @Expose val name: String? = null,
    @Expose val html_url: String? = null,
    @Expose val forks: Int? = null,
    @Expose val watchers: Int? = null,
    @Expose val owner: GithubUser? = null,
    @ColumnInfo(name = "user_id") val userId: Int,
    @PrimaryKey @Expose val id: Int
) : Parcelable