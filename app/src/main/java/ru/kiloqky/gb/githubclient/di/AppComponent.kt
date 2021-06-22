package ru.kiloqky.gb.githubclient.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.presentation.IScreens
import ru.kiloqky.gb.githubclient.presentation.MainActivity
import ru.kiloqky.gb.githubclient.presentation.Screens
import ru.kiloqky.gb.githubclient.presentation.repo.RepoPresenter
import ru.kiloqky.gb.githubclient.presentation.user.UserPresenter
import ru.kiloqky.gb.githubclient.presentation.users.UsersPresenter
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        UserUiModule::class,
        GithubApiModule::class,
        GithubStorageModule::class,
        GithubUserDataSourceModule::class,
        GithubUserRepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withScreens(screens: IScreens): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): AppComponent
    }
}