package ru.kiloqky.gb.githubclient.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.presentation.IScreens
import ru.kiloqky.gb.githubclient.presentation.Screens
import javax.inject.Singleton


@Module
class CiceroneModule {
    private val cicerone by lazy { Cicerone.create() }

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder() = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router() = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = Screens()
}