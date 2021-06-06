package ru.kiloqky.gb.githubclient.presentation.rvinterfaces

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}