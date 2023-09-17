package com.example.scrutinizing_the_service.v2.data.models.response

import android.os.Parcelable
import com.example.scrutinizing_the_service.v2.data.models.response.Tag
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tags(
    @SerializedName("tags")
    val tag: List<Tag> = listOf(),
) : Parcelable