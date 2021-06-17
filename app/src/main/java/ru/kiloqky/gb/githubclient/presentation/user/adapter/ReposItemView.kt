package ru.kiloqky.gb.githubclient.presentation.user.adapter

import ru.kiloqky.gb.githubclient.presentation.rvinterfaces.ItemView

interface ReposItemView: ItemView {
    fun setRepoName(name: String)
}