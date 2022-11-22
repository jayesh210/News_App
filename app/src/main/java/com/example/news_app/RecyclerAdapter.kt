package com.example.news_app

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class RecyclerAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<NewsPojo> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder= NewsViewHolder(view);
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder;
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size;
    }
    fun updateNews(updatedNews:ArrayList<NewsPojo>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) :ViewHolder(itemView){
    val titleView: TextView= itemView.findViewById(R.id.title)
    val image:ImageView=itemView.findViewById(R.id.image)
    val author : TextView=itemView.findViewById(R.id.author)


}
interface NewsItemClicked{
    fun onItemClicked(item: NewsPojo){

    }
}


