package com.yohannes.dev.app.codenews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yohannes.dev.app.codenews.R
import com.yohannes.dev.app.codenews.model.Article

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(NewsComparator) {
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NewsViewHolder(view)
    }

    object NewsComparator: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
             return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val itemTitle: TextView
        val itemDescription: TextView
        val itemAuthor: TextView
        val itemImage: ImageView

        init {
            itemView.apply {
                itemTitle = findViewById(R.id.item_title)
                itemDescription = findViewById(R.id.item_discription)
                itemAuthor = findViewById(R.id.item_author)
                itemImage = findViewById(R.id.item_image)
            }
        }

        fun bind(article: Article) {
            itemView.apply {
                itemTitle.text = article.title
                itemDescription.text = article.description
                itemAuthor.text = article.author
                Glide.with(itemImage.context)
                    .load(article.url)
                    .transform(RoundedCorners(16))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemImage)
            }
        }

    }
}