package com.example.mvvmkotlinexample.adapters;


import android.content.Context;
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmkotlinexample.R

import com.example.mvvmkotlinexample.model.ImgurImageDto;

class RecyclerViewAdapter(
    private val context: Context,
    private val imagelist: List<ImgurImageDto.Data>,
    private val isLinear: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LIST = 1
        private const val VIEW_TYPE_GRID = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLinear) VIEW_TYPE_LIST else VIEW_TYPE_GRID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_LIST) {
            val view = inflater.inflate(R.layout.layout_list_item, parent, false)
            ListViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.layout_grid_item, parent, false)
            GridViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is ListViewHolder) {
            val imageUrl = imagelist[position].link
            Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView)
        } else if (holder is GridViewHolder) {
            val imageUrl = imagelist[position].link
            Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

    private inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    private inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
