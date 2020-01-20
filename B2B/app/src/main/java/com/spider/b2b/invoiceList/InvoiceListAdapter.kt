package com.spider.b2b.invoiceList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spider.b2b.R
import com.spider.b2b.model.InvoiceListResponse
import com.spider.b2b.model.ProductListResponse

class InvoiceListAdapter(private val context: Context,
                         private var repDataList: List<InvoiceListResponse>
) : RecyclerView.Adapter<InvoiceListAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.invoiceID)
        var billAmount: TextView = view.findViewById(R.id.billAmount)
        var gstID: TextView = view.findViewById(R.id.gstID)
    }

    fun setDataList(repDataList: List<InvoiceListResponse>){
        this.repDataList = repDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.invoice_list_row_item, parent, false)

        return RepositoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repoItem = repDataList[position]
        holder.title.text = repoItem.invoiceId.toString()
        holder.billAmount.text = "Rs:"+repoItem.billAmount
        holder.gstID.text = repoItem.gstin

    }

    override fun getItemCount(): Int {
        return repDataList.size
    }
}