package ru.kiloqky.gb.githubclient.presentation.repo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.kiloqky.gb.githubclient.model.entities.Repo

@StateStrategyType(AddToEndStrategy::class)
interface RepoView : MvpView {
    fun showUserAndRepo(repo: Repo)
    fun showError(t: Throwable?)
    fun init()
}