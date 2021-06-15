package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.Provides
import ru.kiloqky.gb.githubclient.scheduler.DefaultSchedulers
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Provides
    fun schedulers(): Schedulers = DefaultSchedulers()
}