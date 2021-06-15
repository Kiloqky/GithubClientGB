package ru.kiloqky.gb.githubclient.presentation.repo

import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Inject

class RepoPresenter(
    private val repoName: String,
    private val repoOwner: String
) : MvpPresenter<RepoView>() {

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var repository: GithubUserRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        viewState.init()
        repository
            .loadRepo(repoName, repoOwner)
            .observeOn(schedulers.main())
            .subscribe(viewState::showUserAndRepo, viewState::showError)
    }
}