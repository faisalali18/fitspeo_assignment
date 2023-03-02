package com.fitspeo_assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitspeo_assignment.models.Album
import com.fitspeo_assignment.retrofit.CodeApi
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val codeApi: CodeApi) {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    suspend fun getAlbums(){
        val result = codeApi.getAlbum()
        if(result.isSuccessful && result.body() != null){
            _albums.postValue(result.body())
        }
    }
}