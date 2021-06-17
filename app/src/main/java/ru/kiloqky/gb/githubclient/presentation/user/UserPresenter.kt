package ru.kiloqky.gb.githubclient.presentation.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.entities.Repo
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.presentation.repo.RepoScreen
import ru.kiloqky.gb.githubclient.presentation.user.adapter.IReposListPresenter
import ru.kiloqky.gb.githubclient.presentation.user.adapter.ReposItemView
import ru.kiloqky.gb.githubclient.scheduler.Schedulers

class UserPresenter(
    private val login: String,
    private val userRepository: GithubUserRepository,
    private val router: Router,
    private val schedulers: Schedulers
) : MvpPresenter<UserView>() {

    private var disposables = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {
        val repos = mutableListOf<Repo>()
        override var itemClickListener: ((ReposItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: ReposItemView) {
            val repo = repos[view.pos]
            repo.name?.let(view::setRepoName)
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        reposListPresenter.itemClickListener = { itemView ->
            navigateToRepoScreen(reposListPresenter.repos[itemView.pos])
        }
        disposables +=
            userRepository
                .loadUserByLogin(login)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(viewState::showUser, viewState::showError)

        disposables +=
            userRepository
                .loadReposFromLogin(login)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(::getReposSuccess, viewState::showError)
    }

    private fun getReposSuccess(list: List<Repo>) {
        reposListPresenter.repos.clear()
        reposListPresenter.repos.addAll(list)
        viewState.updateList()
        Log.d("list", reposListPresenter.repos.toString())
    }

    private fun navigateToRepoScreen(repo: Repo) {
        repo.name?.let { repoName ->
            router.navigateTo(
                repo.owner?.login?.let { repoOwner ->
                    RepoScreen(repoName, repoOwner)
                } ?: RepoScreen("", ""))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}

