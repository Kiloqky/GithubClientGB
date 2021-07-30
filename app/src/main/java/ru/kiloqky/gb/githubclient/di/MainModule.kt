package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kiloqky.gb.githubclient.presentation.MainActivity

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}