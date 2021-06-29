package ru.kiloqky.gb.githubclient.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kiloqky.gb.githubclient.presentation.repo.RepoFragment
import ru.kiloqky.gb.githubclient.presentation.user.UserFragment
import ru.kiloqky.gb.githubclient.presentation.users.UsersFragment

@Module
abstract class UserUiModule {

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun bindRepoFragment(): RepoFragment
}