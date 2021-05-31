package ru.kiloqky.gb.githubclient.presentation.users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.GithubUser
import ru.kiloqky.gb.githubclient.model.GithubUsersRepo
import ru.kiloqky.gb.githubclient.presentation.user.UserScreen
import ru.kiloqky.gb.githubclient.presentation.users.adapter.IUserListPresenter
import ru.kiloqky.gb.githubclient.presentation.users.adapter.UserItemView

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    private fun navigateToUserFragment(user: GithubUser) {
        router.navigateTo(UserScreen(user.login))
    }

}
