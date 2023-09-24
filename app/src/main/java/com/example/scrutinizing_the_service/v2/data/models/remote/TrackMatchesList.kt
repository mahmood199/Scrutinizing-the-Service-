package com.example.scrutinizing_the_service.v2.data.models.remote

import com.google.gson.annotations.SerializedName

data class TrackMatchesList(
    @SerializedName("track")
    val albums: List<Track>
)