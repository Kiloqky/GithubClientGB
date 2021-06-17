package ru.kiloqky.gb.githubclient.presentation.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.App.Navigation.router
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentUsersBinding
import ru.kiloqky.gb.githubclient.helpers.gone
import ru.kiloqky.gb.githubclient.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepositoryFactory
import ru.kiloqky.gb.githubclient.model.user.datasource.cloud.CloudGithubUserDataSource
import ru.kiloqky.gb.githubclient.presentation.users.adapter.UsersRVAdapter
import ru.kiloqky.gb.githubclient.scheduler.SchedulersFactory

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView {

    private val binding: FragmentUsersBinding by viewBinding()

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUserRepositoryFactory.create(),
            router,
            SchedulersFactory.create()
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun init() {
        binding.shimmerLayoutContainer.startShimmer()
        binding.swipeRefresh.setOnRefreshListener(presenter::refresh)
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        binding.swipeRefresh.isRefreshing = false
        binding.shimmerLayoutContainer.gone()
        binding.shimmerLayoutContainer.stopShimmer()
        adapter?.notifyDataSetChanged()
    }

    override fun showError() {
        binding.swipeRefresh.isRefreshing = false
        Toast.makeText(requireContext(), "404", Toast.LENGTH_SHORT).show()
    }
}

