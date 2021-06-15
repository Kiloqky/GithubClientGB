package ru.kiloqky.gb.githubclient.presentation.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.model.entities.Repo
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.presentation.IScreens
import ru.kiloqky.gb.githubclient.presentation.Screens
import ru.kiloqky.gb.githubclient.presentation.user.adapter.IReposListPresenter
import ru.kiloqky.gb.githubclient.presentation.user.adapter.ReposItemView
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Inject

class UserPresenter(
    private val login: String
) : MvpPresenter<UserView>() {

    private var disposables = CompositeDisposable()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var userRepository: GithubUserRepository

    @Inject
    lateinit var schedulers: Schedulers


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
        App.instance.appComponent.inject(this)
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
                    screens.RepoScreen(repoName, repoOwner)
                } ?: screens.RepoScreen("", ""))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}

