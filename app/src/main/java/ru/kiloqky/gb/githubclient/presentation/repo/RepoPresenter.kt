package ru.kiloqky.gb.githubclient.presentation.repo

import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.scheduler.Schedulers

class RepoPresenter(
    private val repoName: String,
    private val repoOwner: String,
    val schedulers: Schedulers,
    val repository: GithubUserRepository
) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        repository
            .loadRepo(repoName, repoOwner)
            .observeOn(schedulers.main())
            .subscribe(viewState::showUserAndRepo, viewState::showError)
    }
}