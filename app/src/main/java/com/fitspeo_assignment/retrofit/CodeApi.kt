package com.fitspeo_assignment.retrofit

import com.fitspeo_assignment.models.Album
import retrofit2.Response
import retrofit2.http.GET

interface CodeApi {

    @GET("photos")
    suspend fun getAlbum() : Response<List<Album>>
}