package com.fitspeo_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.fitspeo_assignment.R
import com.fitspeo_assignment.models.Album
import com.squareup.picasso.Picasso


class AlbumListAdapter (private val list: List<Album>) : RecyclerView.Adapter<AlbumListAdapter.AlbumDataViewHolder>() {
    private lateinit var albumList: Album
    var onItemClick: ((itemList: Album) -> Unit)? = null

    var context: Context? = null
    private var layoutInflater: LayoutInflater? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDataViewHolder {
        if (layoutInflater == null) {
            context = parent.context
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val itemView = layoutInflater!!
            .inflate(R.layout.layout_album_list, parent, false)
        return AlbumDataViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AlbumDataViewHolder, position: Int) {
        albumList = list[position]
        holder.bindView(albumList)
    }

    inner class AlbumDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(album: Album) {

            itemView.findViewById<AppCompatTextView>(R.id.tvLayoutName).text = album.title

            Picasso.with(context).load(album.url).fit().centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.findViewById<AppCompatImageView>(R.id.ivImage))
            itemView.setOnClickListener {
                onItemClick?.invoke(album)
            }
        }
    }

}