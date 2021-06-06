package ru.kiloqky.gb.githubclient.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.githubrest.IGithubUsersRepo
import ru.kiloqky.gb.githubclient.model.githubrest.entities.GithubUser
import ru.kiloqky.gb.githubclient.presentation.user.UserScreen
import ru.kiloqky.gb.githubclient.presentation.users.adapter.IUserListPresenter
import ru.kiloqky.gb.githubclient.presentation.users.adapter.UserItemView
import ru.kiloqky.gb.githubclient.scheduler.Schedulers

class UsersPresenter(
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val schedulers: Schedulers
) :
    MvpPresenter<UsersView>() {
    private val disposables = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let(view::setLogin)
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            navigateToUserFragment(usersListPresenter.users[itemView.pos])
        }
    }

    private fun loadData() {
        disposables +=
            usersRepo
                .loadUsers()
                .observeOn(schedulers.main())
                .subscribe(::onGetUsersSuccess, ::onGetUsersError)

    }

    private fun onGetUsersSuccess(list: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(list)
        viewState.updateList()
    }

    private fun onGetUsersError(t: Throwable?) {
        t?.printStackTrace()
    }

    private fun navigateToUserFragment(githubUser: GithubUser) {
        githubUser.login?.let { router.navigateTo(UserScreen(it)) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

}
