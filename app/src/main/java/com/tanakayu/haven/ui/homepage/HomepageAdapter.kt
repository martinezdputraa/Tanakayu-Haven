package com.tanakayu.haven.ui.homepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanakayu.haven.databinding.LayoutProjectItemBinding
import com.tanakayu.haven.datamodel.ProjectDataModel

class HomepageAdapter (
    private val listener: OnItemSelectedListener):
    RecyclerView.Adapter<HomepageAdapter.ViewHolder> () {

    private val projects = mutableListOf<ProjectDataModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutProjectItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = projects[position]
        holder.bind(item)
    }

    override fun getItemCount() = projects.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ProjectDataModel>) {
        projects.clear()
        projects.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: ProjectDataModel)
    }

    inner class ViewHolder(private val binding: LayoutProjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProjectDataModel) {
            binding.run {
                viewModel = item
                executePendingBindings()

                layoutContainer.setOnClickListener {
                    listener.onItemSelected(item)
                }
            }
        }
    }
}