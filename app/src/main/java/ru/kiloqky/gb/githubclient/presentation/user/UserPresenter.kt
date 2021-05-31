package ru.kiloqky.gb.githubclient.presentation.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.kiloqky.gb.githubclient.model.GithubUsersRepo

class UserPresenter(
    private val userid: String,
    private val userRepository: GithubUsersRepo,
    private val router: Router
): MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userRepository.getUserById(userid)
            ?.let(viewState::showUser)
    }
}

