package com.example.scrutinizing_the_service.v2.data.models.remote

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val offsetAttribute: OffsetAttribute,
    @SerializedName("track")
    val track: List<Track>
)