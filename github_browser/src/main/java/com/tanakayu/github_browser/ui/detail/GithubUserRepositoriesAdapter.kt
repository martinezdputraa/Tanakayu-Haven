package com.tanakayu.github_browser.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanakayu.github_browser.databinding.GithubLayoutUserRepositoriesItemBinding
import com.tanakayu.github_browser.datamodel.GithubUserRepositoryDataModel

class GithubUserRepositoriesAdapter : RecyclerView.Adapter<GithubUserRepositoriesAdapter.UserRepositoriesItemViewHolder>(){

    private val userRepositories = mutableListOf<GithubUserRepositoryDataModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRepositoriesItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = GithubLayoutUserRepositoriesItemBinding.inflate(layoutInflater, parent, false)
        return UserRepositoriesItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserRepositoriesItemViewHolder, position: Int) {
        val item = userRepositories[position]
        holder.bind(item)
    }

    override fun getItemCount() = userRepositories.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userRepos: List<GithubUserRepositoryDataModel>) {
        userRepositories.clear()
        userRepositories.addAll(userRepos)
        notifyDataSetChanged()
    }

    inner class UserRepositoriesItemViewHolder(private val binding: GithubLayoutUserRepositoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubUserRepositoryDataModel) {
            binding.run {
                viewModel = item
                executePendingBindings()
            }
        }
    }
}