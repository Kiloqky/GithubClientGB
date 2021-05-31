package ru.kiloqky.gb.githubclient.presentation.user

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val userId: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory) = UserFragment.newInstance(userId)
}