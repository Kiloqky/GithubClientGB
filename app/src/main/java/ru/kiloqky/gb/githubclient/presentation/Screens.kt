package ru.kiloqky.gb.githubclient.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kiloqky.gb.githubclient.presentation.repo.RepoFragment
import ru.kiloqky.gb.githubclient.presentation.user.UserFragment
import ru.kiloqky.gb.githubclient.presentation.users.UsersFragment

class Screens : IScreens {
    override fun UsersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun UserScreen(login: String) = FragmentScreen {
        UserFragment.newInstance(login)
    }

    override fun RepoScreen(name: String, repoOwner: String) = FragmentScreen {
        RepoFragment.newInstance(name, repoOwner)
    }
}