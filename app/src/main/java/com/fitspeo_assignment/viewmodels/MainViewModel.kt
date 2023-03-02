package com.fitspeo_assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitspeo_assignment.models.Album
import com.fitspeo_assignment.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AlbumRepository) : ViewModel(){

    val albumsLiveData : LiveData<List<Album>>
        get() = repository.albums

    init {
        viewModelScope.launch {
            delay(1000)
            repository.getAlbums()
        }
    }

}