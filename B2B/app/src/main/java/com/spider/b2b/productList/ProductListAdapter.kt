package com.spider.b2b.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spider.b2b.R
import com.spider.b2b.model.ProductListResponse

class ProductListAdapter(
    private val context: Context,
    private var repDataList: List<ProductListResponse>
) : RecyclerView.Adapter<ProductListAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_name)
        var shortDesc: TextView = view.findViewById(R.id.price)
        var imageView: ImageView = view.findViewById(R.id.product_imageView)
    }

    fun setDataList(repDataList: List<ProductListResponse>){
        this.repDataList = repDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_row_item, parent, false)

        return RepositoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repoItem = repDataList[position]
        holder.title.text = repoItem.description
        holder.shortDesc.text = "Rs:"+repoItem.price.toString()
        Glide.with(context)
            .load(repoItem.image.toString())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return repDataList.size
    }
}
