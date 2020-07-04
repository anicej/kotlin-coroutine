package com.anice.myapplication.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anice.myapplication.OnItemClickListener
import com.anice.myapplication.R
import com.anice.myapplication.model.Goods

public class GoodsAdapter(private val list: List<Goods>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GoodsViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        val good: Goods = list[position]
        holder.bind(good)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(good.id,position)
        }
    }

    override fun getItemCount(): Int = list.size

  public  class GoodsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
         var mTitleView: TextView? = null
         var cvItem: CardView? = null


        init {
            mTitleView = itemView.findViewById(R.id.tvTitle)
            cvItem = itemView.findViewById(R.id.cvItem)
        }

        fun bind(good: Goods) {
            mTitleView?.text = good.title
            cvItem?.setCardBackgroundColor(Color.parseColor(good.categoryColor))
            cvItem?.setOnClickListener {
            }

        }

    }

}
