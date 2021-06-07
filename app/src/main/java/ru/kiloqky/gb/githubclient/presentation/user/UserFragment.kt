package ru.kiloqky.gb.githubclient.presentation.user

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.App.Navigation.router
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentUserBinding
import ru.kiloqky.gb.githubclient.helpers.arguments
import ru.kiloqky.gb.githubclient.helpers.gone
import ru.kiloqky.gb.githubclient.helpers.visible
import ru.kiloqky.gb.githubclient.model.githubrest.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.githubrest.ApiHolder
import ru.kiloqky.gb.githubclient.model.githubrest.RetrofitGithubUserRepo
import ru.kiloqky.gb.githubclient.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.githubclient.presentation.user.adapter.ReposRVAdapter
import ru.kiloqky.gb.githubclient.scheduler.SchedulersFactory

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView {

    private val binding: FragmentUserBinding by viewBinding()

    private var adapter: ReposRVAdapter? = null

    companion object {
        private const val ARG_USER_ID = "userId"

        fun newInstance(userId: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_ID to userId)
    }

    private val userId: String by lazy {
        arguments?.getString(ARG_USER_ID) ?: ""
    }

    private val imageLoader: GlideImageLoader by lazy {
        GlideImageLoader()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(userId, RetrofitGithubUserRepo(ApiHolder().api), router, SchedulersFactory.create())
    }

    override fun init() {
        binding.userContainer.gone()
        binding.shimmerLayoutContainer.visible()
        binding.shimmerLayoutContainer.startShimmer()
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding.rvRepos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showUser(githubUser: GithubUser) {
        with(binding) {
            shimmerLayoutContainer.gone()
            userContainer.visible()
            shimmerLayoutContainer.stopShimmer()
            login
            login.text = githubUser.login
            name.text = githubUser.name
            repoCount.text = githubUser.public_repos.toString()
            followers.text = githubUser.followers.toString()
            githubUser.avatarUrl?.let { url ->
                imageLoader.loadInto(url, ivUserAvatar)
            }
        }
    }

    override fun showError(t: Throwable) {
        binding.login.text = t.message
    }
}