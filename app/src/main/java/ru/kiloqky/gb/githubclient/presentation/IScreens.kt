package ru.kiloqky.gb.githubclient.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {

    fun UserScreen(login:String): FragmentScreen

    fun UsersScreen(): FragmentScreen

    fun RepoScreen(name: String, repoOwner: String): FragmentScreen
}