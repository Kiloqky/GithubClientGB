package ru.kiloqky.gb.githubclient.presentation.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kiloqky.gb.githubclient.databinding.ItemReposBinding

class ReposRVAdapter(
    private val presenter: IReposListPresenter
) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val binding: ItemReposBinding) :
        RecyclerView.ViewHolder(binding.root),
        ReposItemView {
        override fun setRepoName(name: String) {
            binding.repoName.text = name
        }

        override var pos = -1
    }
}