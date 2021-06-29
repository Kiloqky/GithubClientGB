package ru.kiloqky.gb.githubclient.presentation.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentUsersBinding
import ru.kiloqky.gb.githubclient.helpers.gone
import ru.kiloqky.gb.githubclient.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.presentation.IScreens
import ru.kiloqky.gb.githubclient.presentation.abs.AbsFragment
import ru.kiloqky.gb.githubclient.presentation.users.adapter.UsersRVAdapter
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Inject

class UsersFragment : AbsFragment(R.layout.fragment_users), UsersView {

    private val binding: FragmentUsersBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repository: GithubUserRepository

    @Inject
    lateinit var schedulers: Schedulers

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(router, screens, repository, schedulers)
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

