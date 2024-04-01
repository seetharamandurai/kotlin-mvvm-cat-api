package com.catapi.task.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catapi.task.data.model.ImageListModelItem
import com.catapi.task.databinding.RecyclerLayoutBinding
import com.catapi.task.view.ImageListFragment
import com.catapi.task.view.OnLastItemVisibleListener

class ImageListAdapter(val onLastItemVisibleListener: OnLastItemVisibleListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listBinding: RecyclerLayoutBinding
    var items: ArrayList<ImageListModelItem> = arrayListOf()

    fun setData(data: List<ImageListModelItem?>?) {
        data?.forEach {
            this.items.add(it!!)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        listBinding = RecyclerLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ListItemHolder(listBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ListItemHolder(private val binding: RecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageListModelItem) {
            binding.list = data
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListItemHolder).bind(items.get(position))
        checkLastItemVisible(position)
    }

    fun checkLastItemVisible(position: Int) {
        if (position == items.size - 1) {
            onLastItemVisibleListener.onLastItemVisible()
        }
    }
}
