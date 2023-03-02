package com.fitspeo_assignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Album (
    @SerializedName("albumId")
    @Expose
    val albumId: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String,
    @SerializedName("title")
    @Expose
    val title: String
) : Serializable

