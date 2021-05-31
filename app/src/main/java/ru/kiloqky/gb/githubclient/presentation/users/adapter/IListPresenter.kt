package ru.kiloqky.gb.githubclient.presentation.users.adapter

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}