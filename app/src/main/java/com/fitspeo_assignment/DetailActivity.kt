package com.fitspeo_assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fitspeo_assignment.databinding.ActivityDetailBinding
import com.fitspeo_assignment.models.Album
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var album: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setData()
    }

    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    private fun setData() {
        album = intent.getSerializableExtra("serializable") as Album?
        binding.tvTitle.text = "Id : " + album!!.id  + " \n\n" + "Title : "+album!!.title
        Picasso.with(this).load(album!!.url).fit().centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivImage)
    }
}