package ru.kiloqky.gb.githubclient.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.App.Navigation.router
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentUserBinding
import ru.kiloqky.gb.githubclient.helpers.arguments
import ru.kiloqky.gb.githubclient.model.GithubUser
import ru.kiloqky.gb.githubclient.model.GithubUsersRepo

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView {
    var binding: FragmentUserBinding? = null

    companion object {
        private const val ARG_USER_ID = "userId"

        fun newInstance(userId: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_ID to userId)
    }

    private val userId: String by lazy {
        arguments?.getString(ARG_USER_ID) ?: ""
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(userId, GithubUsersRepo())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater)
        return binding?.root
    }

    override fun showUser(user: GithubUser) {
        binding?.login?.text = user.login
    }

    override fun showError(t: Throwable) {
        binding?.login?.text = t.message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}