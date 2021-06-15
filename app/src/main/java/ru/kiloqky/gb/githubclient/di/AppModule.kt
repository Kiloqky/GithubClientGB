package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.App
@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}