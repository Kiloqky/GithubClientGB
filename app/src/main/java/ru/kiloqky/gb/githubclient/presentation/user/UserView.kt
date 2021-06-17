package ru.kiloqky.gb.githubclient.presentation.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.kiloqky.gb.githubclient.model.entities.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUser(githubUser: GithubUser)
    fun showError(t: Throwable)
    fun init()
    fun updateList()
}