package com.fitspeo_assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.fitspeo_assignment.adapter.AlbumListAdapter
import com.fitspeo_assignment.databinding.ActivityMainBinding
import com.fitspeo_assignment.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var albumDataAdapter: AlbumListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = mainViewModel

        prepareView()
    }

    private fun prepareView() {
        binding.recycleViewData.itemAnimator = DefaultItemAnimator()
        binding.recycleViewData.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.networkDataProgressBar.visibility = View.VISIBLE
        mainViewModel.albumsLiveData.observe(this) {
            binding.networkDataProgressBar.visibility = View.GONE
            albumDataAdapter = AlbumListAdapter(it)
            this.binding.recycleViewData.adapter = albumDataAdapter

            albumDataAdapter.onItemClick = { album ->

                val sendAlbumData = Intent(this@MainActivity, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("serializable", album)
                sendAlbumData.putExtras(bundle)
                startActivity(sendAlbumData)
            }
        }
    }
}