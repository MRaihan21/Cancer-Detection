package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.NewsItem
import com.dicoding.asclepius.view.NewsActivity

class NewsAdapter : androidx.recyclerview.widget.ListAdapter<NewsItem, NewsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imgNews: ImageView = itemView.findViewById(R.id.news_image)
        private val titleNews: TextView = itemView.findViewById(R.id.news_title_text)
        private val descNews: TextView = itemView.findViewById(R.id.news_desc_text)

        fun bind(newsItem: NewsItem) {
            titleNews.text = newsItem.title
            itemView.setOnClickListener {
                newsItem.url?.let { url ->
                    (itemView.context as? NewsActivity)?.openUrl(url)
                }
            }
            if (newsItem.imageUrl.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(newsItem.imageUrl)
                    .placeholder(R.drawable.ic_image_error)
                    .error(R.drawable.ic_image_error)
                    .into(imgNews)
            } else {
                imgNews.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_image_error
                    )
                )
            }
            descNews.text = newsItem.description
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<NewsItem>(){
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }

}