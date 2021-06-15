package ru.kiloqky.gb.githubclient.di

import dagger.Component
import ru.kiloqky.gb.githubclient.presentation.MainActivity
import ru.kiloqky.gb.githubclient.presentation.repo.RepoPresenter
import ru.kiloqky.gb.githubclient.presentation.user.UserPresenter
import ru.kiloqky.gb.githubclient.presentation.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        GithubApiModule::class,
        GithubStorageModule::class,
        GithubUserDataSourceModule::class,
        GithubUserRepositoryModule::class,
        SchedulersModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(userPresenter: UserPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoPresenter: RepoPresenter)
}