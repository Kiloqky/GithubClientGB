package ru.kiloqky.gb.githubclient.presentation.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.kiloqky.gb.githubclient.databinding.ItemGithubUserBinding
import ru.kiloqky.gb.githubclient.model.imageloader.IImageLoader

class UsersRVAdapter(private val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGithubUserBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(binding) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, binding.ivUserAvatar)
        }
    }
}