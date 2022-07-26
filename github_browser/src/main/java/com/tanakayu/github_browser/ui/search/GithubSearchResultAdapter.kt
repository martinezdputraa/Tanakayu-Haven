package com.tanakayu.github_browser.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanakayu.github_browser.databinding.LayoutSearchUserItemBinding
import com.tanakayu.github_browser.datamodel.GithubUserDetailDataModel

class GithubSearchResultAdapter(
    private val onUserSelectedListener: OnUserSelectedListener)
    : RecyclerView.Adapter<GithubSearchResultAdapter.SearchUserItemViewHolder>() {

    private val users = mutableListOf<GithubUserDetailDataModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchUserItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutSearchUserItemBinding.inflate(layoutInflater, parent, false)
        return SearchUserItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holderSearchUserItem: SearchUserItemViewHolder, position: Int) {
        val item = users[position]
        holderSearchUserItem.bind(item)
    }

    override fun getItemCount() = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userDataModels: List<GithubUserDetailDataModel>) {
        users.clear()
        users.addAll(userDataModels)
        notifyDataSetChanged()
    }

    interface OnUserSelectedListener {
        fun onUserSelected(selectedUserDataModel: GithubUserDetailDataModel)
    }

    inner class SearchUserItemViewHolder(private val binding: LayoutSearchUserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GithubUserDetailDataModel) {
            binding.run {
                viewModel = item
                executePendingBindings()

                layoutContainer.setOnClickListener {
                    onUserSelectedListener.onUserSelected(item)
                }
            }
        }
    }
}