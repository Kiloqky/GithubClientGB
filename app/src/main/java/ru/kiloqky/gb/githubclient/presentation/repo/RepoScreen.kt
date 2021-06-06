package ru.kiloqky.gb.githubclient.presentation.repo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RepoScreen(private val name: String, private val repoOwner: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        RepoFragment.newInstance(name, repoOwner)
}