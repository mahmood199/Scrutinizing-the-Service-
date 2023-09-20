package com.example.scrutinizing_the_service.v2.data.models.response

import android.os.Parcelable
import com.example.scrutinizing_the_service.v2.data.models.response.Artist
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Similar(
    @SerializedName("artist")
    val artist: List<Artist> = listOf(),
) : Parcelable