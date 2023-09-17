package com.example.scrutinizing_the_service.v2.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("@attr")
    val rank: Rank = Rank(),
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("bio")
    val bio: Bio = Bio(),
    @SerializedName("ontour")
    val onTour: String = "",
    @SerializedName("similar")
    val similar: Similar = Similar(),
    @SerializedName("stats")
    val stats: Stats = Stats(),
    @SerializedName("tags")
    val tags: Tags = Tags(),
) : Parcelable