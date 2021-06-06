package ru.kiloqky.gb.githubclient.presentation.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.kiloqky.gb.githubclient.model.GithubUser
@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun showUser(user: GithubUser)
    fun showError(t: Throwable)
}