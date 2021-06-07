package ru.kiloqky.gb.githubclient.presentation.repo

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.databinding.FragmentReposBinding
import ru.kiloqky.gb.githubclient.helpers.arguments
import ru.kiloqky.gb.githubclient.helpers.click
import ru.kiloqky.gb.githubclient.helpers.gone
import ru.kiloqky.gb.githubclient.helpers.visible
import ru.kiloqky.gb.githubclient.model.githubrest.ApiHolder
import ru.kiloqky.gb.githubclient.model.githubrest.RetrofitGithubUserRepo
import ru.kiloqky.gb.githubclient.model.githubrest.entities.Repo
import ru.kiloqky.gb.githubclient.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.githubclient.scheduler.SchedulersFactory

class RepoFragment : MvpAppCompatFragment(R.layout.fragment_repos), RepoView {
    private val binding: FragmentReposBinding by viewBinding()

    companion object {
        private const val ARG_REPO_NAME = "repoName"
        private const val ARG_REPO_OWNER = "repoOwner"
        fun newInstance(repoName: String, repoOwner: String) = RepoFragment()
            .apply {
                arguments(
                    ARG_REPO_NAME to repoName,
                    ARG_REPO_OWNER to repoOwner
                )
            }
    }

    private val repoName: String by lazy {
        arguments?.getString(ARG_REPO_NAME) ?: ""
    }

    private val repoOwner: String by lazy {
        arguments?.getString(ARG_REPO_OWNER) ?: ""
    }

    private val imageLoader: GlideImageLoader by lazy {
        GlideImageLoader()
    }



    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            repoName, repoOwner, RetrofitGithubUserRepo(ApiHolder().api),
            SchedulersFactory.create()
        )
    }

    override fun showUserAndRepo(repo: Repo) {
        with(binding) {
            shimmerLayoutContainer.gone()
            contentContainer.visible()
            shimmerLayoutContainer.stopShimmer()
            login.text = repo.owner?.login
            repo.owner?.avatarUrl?.let { url ->
                imageLoader.loadInto(url, ivUserAvatar)
            }
            repoName.text = repo.name
            forks.text = repo.forks.toString()
            watchers.text = repo.watchers.toString()
            btnMore.click { onMoreClicked(repo) }
        }
    }

    private fun onMoreClicked(repo: Repo) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(repo.html_url)
            )
        )
    }

    override fun showError(t: Throwable?) {
        Toast.makeText(requireContext(), t?.message, Toast.LENGTH_SHORT).show()
    }

    override fun init() {
        with(binding){
            shimmerLayoutContainer.visible()
            contentContainer.gone()
            shimmerLayoutContainer.startShimmer()
        }
    }
}