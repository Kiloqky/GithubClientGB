package ru.kiloqky.gb.githubclient.presentation.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.githubrest.IGithubUsersRepo
import ru.kiloqky.gb.githubclient.model.githubrest.entities.Repo
import ru.kiloqky.gb.githubclient.presentation.repo.RepoScreen
import ru.kiloqky.gb.githubclient.presentation.user.adapter.IReposListPresenter
import ru.kiloqky.gb.githubclient.presentation.user.adapter.ReposItemView
import ru.kiloqky.gb.githubclient.scheduler.Schedulers

class UserPresenter(
    private val login: String,
    private val userRepository: IGithubUsersRepo,
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
                .loadUser(login)
                .observeOn(schedulers.main())
                .doAfterSuccess { user ->
                    user.repos_url?.let { url ->
                        Log.d("repos", "from url")
                        disposables +=
                            userRepository
                                .loadReposFromUrl(url)
                                .observeOn(schedulers.main())
                                .subscribe(::getReposSuccess, viewState::showError)
                    } ?: user.login?.let { login ->
                        Log.d("repos", "from login")
                        userRepository
                            .loadReposFromLogin(login)
                            .observeOn(schedulers.main())
                            .subscribe(::getReposSuccess, viewState::showError)
                            .addTo(disposables)
                    }
                }
                .subscribe(viewState::showUser, viewState::showError)


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

