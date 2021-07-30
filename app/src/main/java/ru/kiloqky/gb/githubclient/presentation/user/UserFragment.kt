package ru.kiloqky.gb.githubclient.presentation.user

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentUserBinding
import ru.kiloqky.gb.githubclient.helpers.arguments
import ru.kiloqky.gb.githubclient.helpers.gone
import ru.kiloqky.gb.githubclient.helpers.visible
import ru.kiloqky.gb.githubclient.model.entities.GithubUser
import ru.kiloqky.gb.githubclient.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.githubclient.model.user.GithubUserRepository
import ru.kiloqky.gb.githubclient.presentation.IScreens
import ru.kiloqky.gb.githubclient.presentation.abs.AbsFragment
import ru.kiloqky.gb.githubclient.presentation.user.adapter.ReposRVAdapter
import ru.kiloqky.gb.githubclient.scheduler.Schedulers
import javax.inject.Inject

class UserFragment : AbsFragment(R.layout.fragment_user), UserView {

    private val binding: FragmentUserBinding by viewBinding()

    private var adapter: ReposRVAdapter? = null

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var userRepository: GithubUserRepository

    @Inject
    lateinit var schedulers: Schedulers

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
        UserPresenter(userId, router, screens, userRepository, schedulers)
    }

    override fun init() {
        binding.userContainer.gone()
        binding.shimmerLayoutContainer.visible()
        binding.shimmerLayoutContainer.startShimmer()
        binding.recyclerViewShimmer.startShimmer()
        binding.recyclerViewShimmer.visible()
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding.rvRepos.adapter = adapter
    }

    override fun updateList() {
        binding.recyclerViewShimmer.stopShimmer()
        binding.recyclerViewShimmer.gone()
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