package com.spider.b2b.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spider.b2b.OptionsInteractionListener
import com.spider.b2b.R
import com.spider.b2b.model.Option
import kotlinx.android.synthetic.main.item_main_options.view.*

class OptionListAdapter(
    private val mListener: OptionsInteractionListener,
    private val data: ArrayList<Option>
) :
    RecyclerView.Adapter<OptionListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_options, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = data[position]
        holder.bindView(option)
    }

    class ViewHolder(val mView: View, val listener: OptionsInteractionListener) : RecyclerView.ViewHolder(mView){
        fun bindView(option: Option){
            mView.apply {
                cv_container.setOnClickListener {
                    listener.onClick(option.id)
                }
                imgMenuOption.setImageResource(option.iconImgResourceId)
                tvTitle.text = option.title
            }
        }
    }
}
