package com.example.mvvmkotlinexample.adapters;


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.model.ImgurImageDto


class RecyclerViewAdapter(
    private val context: Context,
    private var imagelist: List<ImgurImageDto.Data>,
    private val isLinear: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() ,Filterable {
    private lateinit var filteredList: List<ImgurImageDto.Data>
    companion object {
        private const val VIEW_TYPE_LIST = 1
        private const val VIEW_TYPE_GRID = 2
    }
    init {
        this.filteredList = ArrayList(imagelist)
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


        if (holder is ListViewHolder ) {
            val imageUrl = imagelist[position].link
            Glide.with(context)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .dontTransform()
                )
                .into(holder.imageView)

            holder.title.text = imagelist[position].name.toString();
            holder.desc.text = imagelist[position].datetime.toString();
        } else if (holder is GridViewHolder) {
            val imageUrl = imagelist[position].link
            Glide.with(context)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .dontTransform()
                )
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

    private inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.desc)
    }

    private inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }



    private val itemFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val searchText = constraint.toString().toLowerCase()
            val filteredItems: MutableList<ImgurImageDto.Data> = ArrayList()
            for (item in imagelist) {
                if (item.name.toLowerCase().contains(searchText)) {
                    filteredItems.add(item)
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredItems
            filterResults.count = filteredItems.size
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            filteredList = results.values as List<ImgurImageDto.Data>
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

}
