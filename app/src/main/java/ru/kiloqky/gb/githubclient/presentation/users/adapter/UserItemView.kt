package ru.kiloqky.gb.githubclient.presentation.users.adapter

import ru.kiloqky.gb.githubclient.presentation.rvinterfaces.ItemView

interface UserItemView: ItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}